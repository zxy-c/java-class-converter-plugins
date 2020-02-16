package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.EnumClassEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.EnumClassEvaluatorProvider

class JavaEnumClassEvaluatorProvider : EnumClassEvaluatorProvider {
    override fun create(psiNamedElement: PsiNamedElement): EnumClassEvaluator<*, *>? {
        return if (psiNamedElement is PsiClass) {
            JavaEnumClassEvaluator(psiNamedElement)
        } else {
            null
        }
    }
}