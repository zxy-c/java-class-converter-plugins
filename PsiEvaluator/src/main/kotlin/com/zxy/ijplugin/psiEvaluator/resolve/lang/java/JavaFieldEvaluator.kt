/*
 *    Copyright (c) [$year] [zxy]
 *    [java-class-converter-plugins] is licensed under the Mulan PSL v1.
 *    You can use this software according to the terms and conditions of the Mulan PSL v1.
 *    You may obtain a copy of Mulan PSL v1 at:
 *       http://license.coscl.org.cn/MulanPSL
 *    THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 *    PURPOSE.
 */

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