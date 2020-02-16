package com.zxy.ijplugin.psiEvaluator.resolve.lang.kotlin

import com.zxy.ijplugin.psiEvaluator.resolve.AbstractFieldEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.utils.findChildOfType
import org.jetbrains.kotlin.idea.inspections.findExistingEditor
import org.jetbrains.kotlin.idea.intentions.SpecifyTypeExplicitlyIntention
import org.jetbrains.kotlin.idea.util.findAnnotation
import org.jetbrains.kotlin.kdoc.psi.api.KDoc
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.nj2k.postProcessing.type
import org.jetbrains.kotlin.psi.*

class KotlinFieldEvaluator(field: KtNamedDeclaration) :
    AbstractFieldEvaluator<KtNamedDeclaration, KtAnnotationEntry, KDoc, KtTypeReference>(
        field
    ) {
    override fun getAnnotation(fqName: String): KtAnnotationEntry? {
        return this.field.findAnnotation(FqName(fqName))
    }

    override fun getDoc(): KDoc? {
        return this.field.docComment
    }

    override fun getType(): KtTypeReference? {
        return when (this.field) {
            is KtParameter -> {
                this.field.typeReference
            }
            is KtProperty -> {
                this.field.typeReference
            }
            else -> {
                null
            }
        }.let { ktTypeReference ->
            if (ktTypeReference == null) {
                // 自动推断类型
                val kotlinType = this.field.type()
                if (kotlinType == null) {
                    null
                } else {
                    this.field.context?.let {
                        val dummyField = KtPsiFactory(field.project).createAnalyzableFile(
                            "dummy.kt",
                            this.field.text,
                            it
                        ).findChildOfType<KtProperty>()!!
                        SpecifyTypeExplicitlyIntention.addTypeAnnotation(
                            dummyField.findExistingEditor(),
                            dummyField,
                            kotlinType
                        )
                        dummyField.typeReference
                    }
                }
            } else {
                ktTypeReference
            }
        }
    }

    override fun isFinal(): Boolean {
        return this.field.node.findChildByType(KtTokens.VAL_KEYWORD) != null
    }

    override fun isPublic(): Boolean {
        return !this.field.hasModifier(KtTokens.PROTECTED_KEYWORD) && !this.field.hasModifier(KtTokens.PRIVATE_KEYWORD)
    }
}