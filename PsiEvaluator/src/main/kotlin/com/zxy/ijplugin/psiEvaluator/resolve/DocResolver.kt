package com.zxy.ijplugin.psiEvaluator.resolve

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement

interface DocResolverProvider {
    companion object {
        @JvmField
        val EXTENSION_POINT =
            ExtensionPointName<DocResolverProvider>("com.zxy.ijplugin.psiEvaluator.docResolverProvider")
    }

    fun create(psiElement: PsiElement): DocResolver?
}

interface DocResolver {

    companion object {
        fun create(psiElement: PsiElement): DocResolver? {
            if (psiElement is PsiNamedElement) {
                for (provider in DocResolverProvider.EXTENSION_POINT.extensions) {
                    val resolver = provider.create(psiElement)
                    if (resolver != null) {
                        return resolver
                    }
                }
            }
            return null
        }
    }

    fun getDocContent(): String?

}

/**
 * @param D Doc Element
 */
abstract class AbstractDocResolver<D : PsiElement>(protected val docElement: D) : DocResolver