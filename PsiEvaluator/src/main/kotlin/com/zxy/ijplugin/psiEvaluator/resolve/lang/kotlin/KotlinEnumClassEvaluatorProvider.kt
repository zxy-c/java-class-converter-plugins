package com.zxy.ijplugin.psiEvaluator.resolve.lang.kotlin

import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.EnumClassEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.EnumClassEvaluatorProvider
import org.jetbrains.kotlin.psi.KtClass

class KotlinEnumClassEvaluatorProvider : EnumClassEvaluatorProvider {
    override fun create(psiNamedElement: PsiNamedElement): EnumClassEvaluator<*, *>? {
        return if (psiNamedElement is KtClass) {
            KotlinEnumClassEvaluator(psiNamedElement)
        } else {
            null
        }
    }
}