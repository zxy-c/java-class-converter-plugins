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

interface ClassEvaluatorProvider {
    companion object {
        @JvmField
        val EXTENSION_POINT =
            ExtensionPointName<ClassEvaluatorProvider>("com.zxy.ijplugin.psiEvaluator.classEvaluatorProvider")
    }

    fun create(psiNamedElement: PsiNamedElement): ClassEvaluator<*, *, *, *>?
}

interface ClassEvaluator<F : PsiNamedElement, D : PsiElement, out T : PsiElement, out TP : PsiElement> {
    /**
     * 判断这个类是否等于或者继承自目标类型
     * @param fqName 目标类型的完全限定名
     */
    fun instanceof(fqName: String): Boolean

    /**
     * 获取类中的非静态，非transient字段
     * 没有限定必须是public是因为私有字段上的注释与注解可以与public方法混合
     */
    fun getFields(): Array<F>

    fun getDoc(): D?

    fun getSupperTypes(): Array<out T>

    fun isEnum(): Boolean

    fun isAny(): Boolean

    /**
     * 获取此类的类型参数
     */
    fun getTypeParameters(): Array<out TP>

    companion object {
        fun create(clazz: PsiElement): ClassEvaluator<*, *, *, *>? {
            if (clazz is PsiNamedElement) {
                for (provider in ClassEvaluatorProvider.EXTENSION_POINT.extensions) {
                    val evaluator = provider.create(clazz)
                    if (evaluator != null) {
                        return evaluator
                    }
                }
            }
            return null
        }
    }

}

abstract class AbstractClassEvaluator<C : PsiNamedElement, F : PsiNamedElement, D : PsiElement, T : PsiElement, TP : PsiElement>(
    protected val clazz: C
) : ClassEvaluator<F, D, T, TP>

fun ClassEvaluator<*, *, *, *>.getDocResolver(): DocResolver? {
    return this.getDoc()?.let {
        DocResolver.create(it)
    }
}