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

package com.zxy.ijplugin.psiEvaluator.resolve.utils

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.*

class ClassMetadataResolver private constructor(private val clazz: PsiNamedElement) {

    companion object{
        fun resolve(clazz: PsiNamedElement): Set<ClassMetadata> {
            return ClassMetadataResolver(clazz).resolve()
        }
    }

    private val otherCustomClasses = mutableSetOf<PsiNamedElement>()

    private val resolvedClasses = mutableSetOf<PsiNamedElement>()

    fun resolve(): Set<ClassMetadata> {
        return this.resolveClass(clazz)
    }

    private fun resolveClass(clazz: PsiNamedElement): Set<ClassMetadata> {
        val result = mutableSetOf<ClassMetadata>()
        if (resolvedClasses.add(clazz)) {
            val name = clazz.name ?: return emptySet()
            val classEvaluator = ClassEvaluator.create(clazz) ?: return emptySet()
            val doc = classEvaluator.getDocResolver()?.getDocContent()
            val fields = classEvaluator.getFields()
            result.add(ClassMetadata(name, fields.mapNotNull(this::resolveClassProperty).toSet(), doc))
            result.addAll(otherCustomClasses.map(this::resolveClass).flatten())
        }
        return result
    }

    private fun resolveClassProperty(property: PsiNamedElement): ClassPropertyMetadata? {
        val fieldEvaluator = FieldEvaluator.create(property) ?: return null
        val name = property.name ?: return null
        val type = fieldEvaluator.getType() ?: return null
        val typeEvaluator = TypeEvaluator.create(type) ?: return null
        return ClassPropertyMetadata(
            name,
            resolveTypeMetadata(type) ?: return null,
            typeEvaluator.isNullable(),
            fieldEvaluator.isFinal(),
            fieldEvaluator.getDocResolver()?.getDocContent()
        )
    }

    private fun resolveTypeMetadata(psiElement: PsiElement): TypeMetadata? {
        if (!psiElement.isValid) return null
        val typeEvaluator = TypeEvaluator.create(psiElement) ?: return null
        val obviousType = typeEvaluator.getObviousType()
        if (obviousType == ObviousType.OBJECT) {
            typeEvaluator.getClass()?.let {
                otherCustomClasses.add(it)
            }
        }
        return TypeMetadata(
            typeEvaluator.getName() ?: return null,
            obviousType,
            typeEvaluator.getTypeParameters()?.mapNotNull(this::resolveTypeMetadata) ?: emptyList()
        )
    }

}