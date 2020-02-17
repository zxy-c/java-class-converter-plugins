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

package com.zxy.ijplugin.psiEvaluator.resolve.lang.kotlin

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.TypeEvaluator

/**
 * Kotlin 的返回值类型 void - Unit 可以省略
 */
class NullTypeEvaluator : TypeEvaluator<PsiElement>{

    override fun getClass(): PsiNamedElement? {
        return null
    }

    override fun isNullable(): Boolean? {
        return null
    }

    override fun getTypeParameters(): Array<out PsiElement>? {
        return null
    }

    override fun getAnnotations(): Array<out PsiElement> {
        return emptyArray()
    }

    override fun isString(): Boolean {
        return false
    }

    override fun isNumber(): Boolean {
        return false
    }

    override fun isArray(): Boolean {
        return false
    }

    override fun isCollection(): Boolean {
        return false
    }

    override fun isBoolean(): Boolean {
        return false
    }

    override fun isVoid(): Boolean {
        return true
    }

    override fun isMap(): Boolean {
        return false
    }

    override fun getOriginType(): PsiElement? {
        return null
    }

    override fun getName(): String? {
        return null
    }
}

