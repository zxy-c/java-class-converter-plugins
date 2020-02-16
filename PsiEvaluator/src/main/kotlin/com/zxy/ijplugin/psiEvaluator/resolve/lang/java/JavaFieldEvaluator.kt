package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiField
import com.intellij.psi.PsiModifier
import com.intellij.psi.PsiTypeElement
import com.intellij.psi.javadoc.PsiDocComment
import com.zxy.ijplugin.psiEvaluator.resolve.AbstractFieldEvaluator

class JavaFieldEvaluator(field: PsiField) :
    AbstractFieldEvaluator<PsiField, PsiAnnotation, PsiDocComment, PsiTypeElement>(field) {
    override fun getAnnotation(fqName: String): PsiAnnotation? {
        return this.field.getAnnotation(fqName)
    }

    override fun getDoc(): PsiDocComment? {
        return this.field.docComment
    }

    override fun getType(): PsiTypeElement? {
        return this.field.typeElement
    }


    override fun isFinal(): Boolean {
        return this.field.hasModifierProperty(PsiModifier.FINAL)
    }

    override fun isPublic(): Boolean {
        return this.field.hasModifierProperty(PsiModifier.PUBLIC)
    }
}