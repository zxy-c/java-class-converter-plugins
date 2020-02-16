package com.zxy.ijplugin.psiEvaluator.resolve.lang.kotlin

import com.intellij.psi.PsiElement
import com.zxy.ijplugin.psiEvaluator.resolve.TypeEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.TypeEvaluatorProvider
import org.jetbrains.kotlin.psi.KtTypeReference

class KotlinTypeEvaluatorProvider : TypeEvaluatorProvider {
    override fun create(type: PsiElement?): TypeEvaluator<*>? {
        return when (type) {
            null -> {
                NullTypeEvaluator()
            }
            is KtTypeReference -> {
                KotlinTypeEvaluator(type)
            }
            else -> {
                null
            }
        }
    }
}