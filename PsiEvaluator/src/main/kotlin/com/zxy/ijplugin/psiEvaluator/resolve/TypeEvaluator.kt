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

package com.zxy.ijplugin.psiEvaluator.resolve

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement

interface TypeEvaluatorProvider {

    companion object {
        @JvmField
        val EXTENSION_POINT =
            ExtensionPointName<TypeEvaluatorProvider>("com.zxy.ijplugin.psiEvaluator.typeEvaluatorProvider")
    }

    fun create(type: PsiElement?): TypeEvaluator<*>?

}

interface TypeEvaluator<TP : PsiElement> {

    /**
     * 获取此类型对应的class元素
     * 可以跨语言
     */
    fun getClass(): PsiNamedElement?

    /**
     * 这个类型是否是可空类型
     * null 表示无法判断
     */
    fun isNullable(): Boolean?

    fun getTypeParameters(): Array<out PsiElement>?

    /**
     * 获取类型上的注解
     */
    fun getAnnotations(): Array<out PsiElement>

    fun isString(): Boolean

    fun isInt(): Boolean

    fun isLong(): Boolean

    fun isDouble(): Boolean

    fun isFloat(): Boolean

    fun isByte(): Boolean

    fun isArray(): Boolean

    fun isCollection(): Boolean

    fun isBoolean(): Boolean

    fun isVoid(): Boolean

    fun isMap(): Boolean

    fun isAny(): Boolean = this.getClassEvaluator()?.isAny() == true

    /**
     * 如果这是一个父类上泛型参数别名
     * 获取其对应的子类泛型参数
     */
    fun getOriginType(): TP?

    fun getName(): String?

    companion object {
        fun create(type: PsiElement?): TypeEvaluator<*>? {
            for (typeEvaluatorProvider in TypeEvaluatorProvider.EXTENSION_POINT.extensions) {
                val typeEvaluator = typeEvaluatorProvider.create(type)
                if (typeEvaluator != null) {
                    return typeEvaluator
                }
            }
            return null
        }
    }
}

abstract class AbstractTypeEvaluator<T : PsiElement, A : PsiElement, TP : PsiElement>(protected val type: T) :
    TypeEvaluator<TP> {

    protected val clazz by lazy {
        this.getClass()
    }

    abstract override fun getTypeParameters(): Array<out T>?

    abstract override fun getAnnotations(): Array<out A>

}

fun TypeEvaluator<*>.getClassEvaluator(): ClassEvaluator<*, *, *, *>? {
    return this.getClass()?.let {
        ClassEvaluator.create(it)
    }
}

fun TypeEvaluator<*>.isNumber(): Boolean {
    return this.isInt() || this.isDouble() || this.isFloat() || this.isLong() || this.isByte()
}

fun TypeEvaluator<*>.isCustomObject(): Boolean {
    return !this.isAny() && !this.isArray() && !this.isCollection() && !this.isMap() && !this.isString()
            && !this.isNumber() && !this.isVoid() && !this.isBoolean() && this.getClassEvaluator() != null
}