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
package com.zxy.ijplugin.javaClassConverterCore.resolve.utils

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.javaClassConverterCore.resolve.*

class ClassMetadataResolver private constructor(private val clazz: PsiNamedElement) {

    companion object {
        fun resolve(clazz: PsiNamedElement): Set<ClassMetadata> {
            return ClassMetadataResolver(clazz).resolve()
        }
    }


    private val resolvedClasses = mutableSetOf<PsiNamedElement>()

    fun resolve(): Set<ClassMetadata> {
        return this.resolveClass(clazz)
    }

    private fun resolveClass(clazz: PsiNamedElement): Set<ClassMetadata> {
        val result = mutableSetOf<ClassMetadata>()
        val otherCustomClasses = mutableSetOf<PsiNamedElement>()
        if (resolvedClasses.add(clazz)) {

            val name = clazz.name ?: return emptySet()
            val classEvaluator = ClassEvaluator.create(clazz) ?: return emptySet()

            val doc = classEvaluator.getDocResolver()?.getDocContent()
            val fields = classEvaluator.getFields()
            val isEnum = classEvaluator.isEnum()
            result.add(
                ClassMetadata(
                    name,
                    fields.mapNotNull { property -> this.resolveClassProperty(property, otherCustomClasses) }.toSet(),
                    doc,
                    isEnum,
                    if (isEnum) {
                        this.resolveEnumClass(clazz)
                    } else {
                        emptySet()
                    }
                )
            )
            result.addAll(otherCustomClasses.map(this::resolveClass).flatten())
        }
        return result
    }

    private fun resolveClassProperty(
        property: PsiNamedElement,
        otherCustomClasses: MutableSet<PsiNamedElement>
    ): ClassPropertyMetadata? {
        val fieldEvaluator = FieldEvaluator.create(property) ?: return null
        val name = property.name ?: return null
        val type = fieldEvaluator.getType() ?: return null
        val typeEvaluator = TypeEvaluator.create(type) ?: return null
        return ClassPropertyMetadata(
            name,
            resolveTypeMetadata(type, otherCustomClasses) ?: return null,
            typeEvaluator.isNullable(),
            fieldEvaluator.isFinal(),
            fieldEvaluator.getDocResolver()?.getDocContent()
        )
    }

    private fun resolveTypeMetadata(
        psiElement: PsiElement,
        otherCustomClasses: MutableSet<PsiNamedElement>
    ): TypeMetadata? {
        if (!psiElement.isValid) return null
        val typeEvaluator = TypeEvaluator.create(psiElement) ?: return null
        val obviousType = typeEvaluator.getObviousType()
        if (obviousType == ObviousType.OBJECT || obviousType == ObviousType.ENUM) {
            typeEvaluator.getClass()?.let {
                otherCustomClasses.add(it)
            }
        }
        return TypeMetadata(
            typeEvaluator.getName() ?: return null,
            obviousType,
            typeEvaluator.getTypeParameters()?.mapNotNull { it ->
                this.resolveTypeMetadata(
                    it,
                    otherCustomClasses
                )
            }
                ?: emptyList()
        )
    }

    private fun resolveEnumClass(psiNamedElement: PsiNamedElement): Set<EnumEntryMetadata> {
        val enumClassEvaluator = EnumClassEvaluator.create(psiNamedElement) ?: return emptySet()
        return enumClassEvaluator.getFields().mapNotNull {
            val fields = FieldEvaluator.create(it) ?: return@mapNotNull null
            val doc = fields.getDocResolver()?.getDocContent()
            val name = it.name ?: return@mapNotNull null
            EnumEntryMetadata(name, doc)
        }.toSet()
    }

}