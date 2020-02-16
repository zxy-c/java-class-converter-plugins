package com.zxy.ijplugin.psiEvaluator.resolve.lang.kotlin

import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.FieldEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.FieldEvaluatorProvider
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtProperty

class KotlinFieldEvaluatorProvider : FieldEvaluatorProvider {
    override fun create(field: PsiNamedElement): FieldEvaluator<*, *, *>? {
        return if ((field is KtProperty || field is KtParameter) && field is KtNamedDeclaration) {
            KotlinFieldEvaluator(field)
        } else if (field is KtEnumEntry) {
            KotlinFieldEvaluator(field)
        } else {
            null
        }
    }
}