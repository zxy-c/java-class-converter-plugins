/*
 *    Copyright (c) [$year] [zxy]
 *    [java-class-converter-plugins] is licensed under the Mulan PSL v1.
 *    You can use this software according to the terms and conditions of the Mulan PSL v1.
 *    You may obtain a copy of Mulan PSL v1 at:
 *       http://license.coscl.org.cn/MulanPSL
 *    THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 *    PURPOSE.
 */

package com.zxy.ijplugin.javaToDart

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.*

object ToDartUtils{
    fun convertClass(clazz: PsiNamedElement): String? {
        val result = StringBuilder()
        val name = clazz.name ?: return null
        val classEvaluator = ClassEvaluator.create(clazz) ?: return null
        val doc = classEvaluator.getDocResolver()?.getDocContent()
        val fields = classEvaluator.getFields()
        doc?.let {
            result.append(it)
        }
        result.append("class $name{\n")
        result.append(fields.mapNotNull(this::convertField).joinToString("\n"))
        result.append("\n}")
        fields.forEach {
            val fieldEvaluator = FieldEvaluator.create(it) ?: return@forEach
            val type = fieldEvaluator.getType() ?: return@forEach
            val typeEvaluator = TypeEvaluator.create(type) ?: return@forEach
            val subClass = typeEvaluator.getClass() ?: return@forEach
            if (typeEvaluator.isCustomObject()) {
                result.append("\n\n")
                result.append(this.convertClass(subClass))
            }
        }
        return result.toString()
    }

    private fun convertField(field: PsiNamedElement): String? {
        val fieldString = StringBuilder()
        val fieldEvaluator = FieldEvaluator.create(field) ?: return null
        val type = fieldEvaluator.getType() ?: return null
        val name = field.name ?: return null
        val typeSection = convertType(type) ?: return ""
        if (fieldEvaluator.isFinal()) {
            fieldString.append("final ")
        }
        fieldString.append(typeSection)
        fieldString.append(" ")
        fieldString.append(name)
        fieldString.append(";")
        return fieldString.toString()
    }

    private fun convertType(type: PsiElement): String? {
        val typeEvaluator = TypeEvaluator.create(type) ?: return null
        return if (typeEvaluator.isBoolean()) {
            "bool"
        } else if (typeEvaluator.isNumber()) {
            "int"
        } else if (typeEvaluator.isString()) {
            "String"
        } else if (typeEvaluator.isMap()) {
            val typeParameters = typeEvaluator.getTypeParameters() ?: return null
            val types = typeParameters.take(2).mapNotNull(this::convertType)
            if (types.size != 2) return null
            "Map<${types[0]},${types[1]}>"
        } else if (typeEvaluator.isCollection() || typeEvaluator.isArray()) {
            val firstType = typeEvaluator.getTypeParameters()?.firstOrNull() ?: return null
            val typeString = this.convertType(firstType) ?: return null
            "List<${typeString}>"
        } else if (typeEvaluator.isAny()) {
            "var"
        } else {
            val typeParameters = typeEvaluator.getTypeParameters() ?: return null
            val types = typeParameters.map(this::convertType)
            if (types.any { it == null }) {
                return null
            }
            val typesString = if (types.isNotEmpty()) {
                "<${types.joinToString(", ")}>"
            } else {
                ""
            }
            "${typeEvaluator.getName()}$typesString"
        }
    }
}