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

interface EnumClassEvaluatorProvider {
    companion object {
        @JvmField
        val EXTENSION_POINT =
            ExtensionPointName<EnumClassEvaluatorProvider>("com.zxy.ijplugin.javaClassConverterCore.enumClassEvaluatorProvider")
    }

    fun create(psiNamedElement: PsiNamedElement): EnumClassEvaluator<*, *>?
}

interface EnumClassEvaluator<F : PsiNamedElement, D : PsiElement> {

    fun getFields(): Array<F>

    fun getDoc(): D?

    companion object {
        fun create(psiElement: PsiElement): EnumClassEvaluator<*, *>? {
            if (psiElement is PsiNamedElement) {
                for (provider in EnumClassEvaluatorProvider.EXTENSION_POINT.extensions) {
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

abstract class AbstractEnumClassEvaluator<C : PsiNamedElement, F : PsiNamedElement, D : PsiElement>(protected val enumClass: C) :
    EnumClassEvaluator<F, D>