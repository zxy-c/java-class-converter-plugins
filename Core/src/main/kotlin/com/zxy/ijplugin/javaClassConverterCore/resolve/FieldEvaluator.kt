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

package com.zxy.ijplugin.javaClassConverterCore.resolve

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement

interface FieldEvaluatorProvider {
    companion object {
        @JvmField
        val EXTENSION_POINT =
            ExtensionPointName<FieldEvaluatorProvider>("com.zxy.ijplugin.javaClassConverterCore.fieldEvaluatorProvider")

    }

    fun create(field: PsiNamedElement): FieldEvaluator<*, *, *>?
}

interface FieldEvaluator<A : PsiElement, D : PsiElement, T : PsiElement> {

    fun getAnnotation(fqName: String): A?

    fun getDoc(): D?

    /**
     * 获取字段的类型
     */
    fun getType(): T?

    /**
     * 判断字段是否是不变的
     */
    fun isFinal(): Boolean

    fun isPublic(): Boolean

    companion object {
        fun create(psiElement: PsiElement): FieldEvaluator<*, *, *>? {
            if (psiElement is PsiNamedElement) {
                for (provider in FieldEvaluatorProvider.EXTENSION_POINT.extensions) {
                    val evaluator = provider.create(psiElement)
                    if (evaluator != null) {
                        return evaluator
                    }
                }
            }
            return null
        }
    }

}

abstract class AbstractFieldEvaluator<F : PsiNamedElement, A : PsiElement, D : PsiElement, T : PsiElement>(protected val field: F) :
    FieldEvaluator<A, D, T>

fun FieldEvaluator<*, *, *>.getTypeEvaluator(): TypeEvaluator<*>? {
    return this.getType()?.let {
        TypeEvaluator.create(it)
    }
}

fun FieldEvaluator<*, *, *>.getDocResolver(): DocResolver? {
    return this.getDoc()?.let {
        DocResolver.create(it)
    }
}