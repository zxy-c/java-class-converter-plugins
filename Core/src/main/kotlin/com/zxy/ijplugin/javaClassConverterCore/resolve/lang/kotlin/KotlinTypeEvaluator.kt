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

package com.zxy.ijplugin.javaClassConverterCore.resolve.lang.kotlin

import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.javaClassConverterCore.resolve.AbstractTypeEvaluator
import com.zxy.ijplugin.javaClassConverterCore.resolve.ClassEvaluator
import org.jetbrains.kotlin.idea.refactoring.memberInfo.qualifiedClassNameForRendering
import org.jetbrains.kotlin.nj2k.postProcessing.resolve
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.containingClass

class KotlinTypeEvaluator(typeReference: KtTypeReference) :
    AbstractTypeEvaluator<KtTypeReference, KtAnnotationEntry, KtTypeParameter>(typeReference) {

    private val userType: KtUserType?
        get() = this.type.typeElement?.let {
            if (it is KtNullableType) {
                it.innerType
            } else {
                it
            }
        } as? KtUserType

    override fun getClass(): PsiNamedElement? {
        return when (val resolveResult = userType?.referenceExpression?.resolve()) {
            is KtClass -> {
                resolveResult
            }
            is KtPrimaryConstructor -> {
                resolveResult.containingClass()
            }
            is PsiNamedElement -> {
                resolveResult
            }
            else -> null
        }
    }

    private val classEvaluator by lazy {
        this.clazz?.let { ClassEvaluator.create(it) }
    }

    private val qualifiedClassName by lazy {
        clazz?.qualifiedClassNameForRendering()
    }


    override fun isNullable(): Boolean? {
        val typeElement = type.typeElement
        return if (typeElement == null) {
            null
        } else typeElement is KtNullableType
    }


    override fun getTypeParameters(): Array<KtTypeReference>? {
        this.type
        return this.type.typeElement?.typeArgumentsAsTypes?.toTypedArray()
    }

    override fun getAnnotations(): Array<out KtAnnotationEntry> {
        return this.type.annotationEntries.toTypedArray()
    }

    override fun isString(): Boolean {
        return qualifiedClassName == "kotlin.String" || qualifiedClassName == "kotlin.Char"
    }

    override fun isInt(): Boolean {
        return qualifiedClassName == "kotlin.Int"
    }

    override fun isLong(): Boolean {
        return qualifiedClassName == "kotlin.Long"
    }

    override fun isDouble(): Boolean {
        return qualifiedClassName == "kotlin.Double"
    }

    override fun isFloat(): Boolean {
        return qualifiedClassName == "kotlin.Float"
    }

    override fun isByte(): Boolean {
        return qualifiedClassName == "kotlin.Float"
    }

    override fun isArray(): Boolean {
        return qualifiedClassName == "kotlin.Array"
    }

    override fun isCollection(): Boolean {
        return classEvaluator?.instanceof("kotlin.collections.Collection") == true
    }

    override fun isBoolean(): Boolean {
        return qualifiedClassName == "kotlin.Boolean"
    }

    override fun isVoid(): Boolean {
        return qualifiedClassName == "kotlin.Unit"
    }

    override fun isMap(): Boolean {
        return classEvaluator?.instanceof("kotlin.collections.Map") == true
    }

    override fun getOriginType(): KtTypeParameter? {
        return this.userType?.referenceExpression?.resolve() as? KtTypeParameter
    }

    override fun getName(): String? {
        return this.userType?.text
    }

}