package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.PsiElement
import com.intellij.psi.javadoc.PsiDocComment
import com.zxy.ijplugin.psiEvaluator.resolve.DocResolver
import com.zxy.ijplugin.psiEvaluator.resolve.DocResolverProvider

class JavaDocResolverProvider : DocResolverProvider {
    override fun create(psiElement: PsiElement): DocResolver? {
        return if (psiElement is PsiDocComment) {
            JavaDocResolver(psiElement)
        } else {
            null
        }
    }
}