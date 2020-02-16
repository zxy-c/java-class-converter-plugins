package com.zxy.ijplugin.psiEvaluator.resolve.lang.kotlin

import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.ClassEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.ClassEvaluatorProvider
import org.jetbrains.kotlin.asJava.classes.KtLightClass
import org.jetbrains.kotlin.psi.KtClass

class KotlinClassEvaluatorProvider : ClassEvaluatorProvider {
    override fun create(psiNamedElement: PsiNamedElement): ClassEvaluator<*, *, *, *>? {
        return when (psiNamedElement) {
            is KtLightClass -> {
                (psiNamedElement.navigationElement as? KtClass)?.let {
                    KotlinClassEvaluator(it)
                }
            }
            is KtClass -> {
                KotlinClassEvaluator(psiNamedElement)
            }
            else -> null
        }
    }

}