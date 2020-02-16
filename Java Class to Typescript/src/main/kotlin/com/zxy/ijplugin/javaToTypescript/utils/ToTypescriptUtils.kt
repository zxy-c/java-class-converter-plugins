package com.zxy.ijplugin.javaToTypescript.utils

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.*

object ToTypescriptUtils {

    fun convertClass(clazz: PsiNamedElement): String? {
        val result = StringBuilder()
        val name = clazz.name ?: return null
        val classEvaluator = ClassEvaluator.create(clazz) ?: return null
        val doc = classEvaluator.getDocResolver()?.getDocContent()
        val fields = classEvaluator.getFields()
        doc?.let {
            result.append(it)
        }
        result.append("interface $name{\n")
        result.append(fields.mapNotNull(this::convertField).joinToString(";\n"))
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
        val typeEvaluator = TypeEvaluator.create(type) ?: return null
        val name = field.name ?: return null
        val typeSection = convertType(type) ?: "unknown"
        val isNullable = typeEvaluator.isNullable()
        if (fieldEvaluator.isFinal()) {
            fieldString.append("readonly ")
        }
        fieldString.append(name)
        fieldString.append(
            if (isNullable == true) {
                "?:"
            } else {
                ":"
            }
        )
        fieldString.append(typeSection)
        return fieldString.toString()
    }

    private fun convertType(type: PsiElement): String? {
        val typeEvaluator = TypeEvaluator.create(type) ?: return null
        return if (typeEvaluator.isBoolean()) {
            "boolean"
        } else if (typeEvaluator.isNumber()) {
            "number"
        } else if (typeEvaluator.isString()) {
            "string"
        } else if (typeEvaluator.isMap()) {
            val typeParameters = typeEvaluator.getTypeParameters() ?: return null
            val types = typeParameters.take(2).mapNotNull(this::convertType)
            if (types.size != 2) return null
            "[key ${types[0]}]: ${types[1]}"
        } else if (typeEvaluator.isCollection() || typeEvaluator.isArray()) {
            val firstType = typeEvaluator.getTypeParameters()?.firstOrNull() ?: return null
            val typeString = this.convertType(firstType) ?: return null
            "Array<${typeString}>"
        } else if (typeEvaluator.isAny()) {
            "any"
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