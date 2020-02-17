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

package com.zxy.ijplugin.psiEvaluator.resolve.utils

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.idea.refactoring.memberInfo.qualifiedClassNameForRendering
import org.jetbrains.kotlin.nj2k.postProcessing.resolve
import org.jetbrains.kotlin.psi.KtClass

/**
 * @see PsiTreeUtil.findChildrenOfType
 */
inline fun <reified T : PsiElement> PsiElement.findChildrenOfType(): MutableCollection<T> {
    return PsiTreeUtil.findChildrenOfType(this, T::class.java)
}

/**
 * @see PsiTreeUtil.findChildOfType
 */
inline fun <reified T : PsiElement> PsiElement.findChildOfType(): T? {
    return PsiTreeUtil.findChildOfType(this, T::class.java)
}

/**
 * @see PsiTreeUtil.getParentOfType
 */
inline fun <reified T : PsiElement> PsiElement.getParentOfType(): T? {
    return PsiTreeUtil.getParentOfType(this, T::class.java)
}

fun KtClass.instanceof(qualifiedName: String): Boolean {
    return this.qualifiedClassNameForRendering() == qualifiedName || this.superTypeListEntries.any { entry ->
        val resolveResult = entry.typeAsUserType?.referenceExpression?.resolve()
        if (resolveResult is KtClass) {
            resolveResult.instanceof(qualifiedName)
        } else {
            false
        }
    }
}