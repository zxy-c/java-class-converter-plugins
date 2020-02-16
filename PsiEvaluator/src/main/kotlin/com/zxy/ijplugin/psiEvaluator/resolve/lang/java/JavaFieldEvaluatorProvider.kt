package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.PsiField
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.FieldEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.FieldEvaluatorProvider

class JavaFieldEvaluatorProvider : FieldEvaluatorProvider {
    override fun create(field: PsiNamedElement): FieldEvaluator<*, *, *>? {
        return if (field is PsiField) {
            JavaFieldEvaluator(field)
        } else {
            null
        }
    }

}