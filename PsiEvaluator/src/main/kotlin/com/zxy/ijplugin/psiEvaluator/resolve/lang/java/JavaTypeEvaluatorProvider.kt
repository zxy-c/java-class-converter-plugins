package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.PsiTypeElement
import com.zxy.ijplugin.psiEvaluator.resolve.TypeEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.TypeEvaluatorProvider

class JavaTypeEvaluatorProvider : TypeEvaluatorProvider {
    override fun create(type: PsiElement?): TypeEvaluator<*>? {
        return when (type) {
            is PsiTypeElement -> {
                JavaTypeElementEvaluator(type)
            }
            is PsiJavaCodeReferenceElement -> {
                JavaCodeReferenceElementTypeEvaluator(type)
            }
            else -> {
                null
            }
        }
    }
}