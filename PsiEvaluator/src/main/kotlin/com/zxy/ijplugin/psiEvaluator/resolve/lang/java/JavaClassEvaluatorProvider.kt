package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.ClassEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.ClassEvaluatorProvider

class JavaClassEvaluatorProvider : ClassEvaluatorProvider {
    override fun create(psiNamedElement: PsiNamedElement): ClassEvaluator<*, *, *, *>? {
        return if (psiNamedElement is PsiClass) {
            JavaClassEvaluator(psiNamedElement)
        } else {
            null
        }
    }
}