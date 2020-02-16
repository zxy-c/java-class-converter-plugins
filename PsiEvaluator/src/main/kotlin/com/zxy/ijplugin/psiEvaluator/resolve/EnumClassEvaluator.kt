package com.zxy.ijplugin.psiEvaluator.resolve

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement

interface EnumClassEvaluatorProvider {
    companion object {
        @JvmField
        val EXTENSION_POINT =
            ExtensionPointName<EnumClassEvaluatorProvider>("com.zxy.ijplugin.psiEvaluator.enumClassEvaluatorProvider")
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