package com.zxy.ijplugin.psiEvaluator.resolve.lang.kotlin

import com.intellij.psi.PsiElement
import com.zxy.ijplugin.psiEvaluator.resolve.DocResolver
import com.zxy.ijplugin.psiEvaluator.resolve.DocResolverProvider
import org.jetbrains.kotlin.kdoc.psi.api.KDoc

class KotlinDocResolverProvider : DocResolverProvider {
    override fun create(psiElement: PsiElement): DocResolver? {
        return if (psiElement is KDoc) {
            KotlinDocResolver(psiElement)
        } else {
            null
        }
    }
}