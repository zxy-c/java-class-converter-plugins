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

import com.intellij.psi.*
import com.intellij.psi.util.PsiTypesUtil
import com.zxy.ijplugin.psiEvaluator.resolve.AbstractTypeEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.ClassEvaluator

abstract class AbstractJavaTypeEvaluator<T : PsiElement>(type: T) :
    AbstractTypeEvaluator<T, PsiAnnotation, PsiTypeParameter>(type) {

    protected abstract val psiType: PsiType

    private val classEvaluator by lazy {
        this.clazz?.let {
            ClassEvaluator.create(it)
        }
    }

    override fun getClass(): PsiClass? {
        return PsiTypesUtil.getPsiClass(this.psiType)
    }

    override fun isNullable(): Boolean? {
        return false
    }

    override fun isString(): Boolean {
        return this.psiType.isAssignableFrom(PsiType.CHAR) || classEvaluator?.instanceof("java.lang.String") == true
    }

    override fun isNumber(): Boolean {
        return psiType.isAssignableFrom(PsiType.DOUBLE) ||
                psiType.isAssignableFrom(PsiType.FLOAT) ||
                psiType.isAssignableFrom(PsiType.INT) ||
                psiType.isAssignableFrom(PsiType.LONG)
    }

    override fun isArray(): Boolean {
        return psiType is PsiArrayType
    }

    override fun isCollection(): Boolean {
        return classEvaluator?.instanceof("java.util.Collection") == true
    }

    override fun isBoolean(): Boolean {
        return psiType.isAssignableFrom(PsiType.BOOLEAN)
    }

    override fun isVoid(): Boolean {
        return psiType.isAssignableFrom(PsiType.VOID)
    }

    override fun isMap(): Boolean {
        return classEvaluator?.instanceof("java.utl.Map") == true
    }
}