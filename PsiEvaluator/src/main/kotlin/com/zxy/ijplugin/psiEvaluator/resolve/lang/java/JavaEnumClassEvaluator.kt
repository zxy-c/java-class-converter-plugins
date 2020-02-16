package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiEnumConstant
import com.intellij.psi.javadoc.PsiDocComment
import com.zxy.ijplugin.psiEvaluator.resolve.AbstractEnumClassEvaluator

class JavaEnumClassEvaluator(enumClass: PsiClass) :
    AbstractEnumClassEvaluator<PsiClass, PsiEnumConstant, PsiDocComment>(
        enumClass
    ) {
    override fun getFields(): Array<PsiEnumConstant> {
        return this.enumClass.fields.filterIsInstance<PsiEnumConstant>().toTypedArray()
    }

    override fun getDoc(): PsiDocComment? {
        return this.enumClass.docComment
    }
}