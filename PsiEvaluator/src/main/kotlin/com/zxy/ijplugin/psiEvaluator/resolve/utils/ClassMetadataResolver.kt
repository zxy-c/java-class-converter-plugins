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

import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.ClassEvaluator

class ClassMetadataResolver(private val clazz: PsiNamedElement) {

    val otherCustomClasses = mutableSetOf(clazz)

    val resolvedClasses = mutableSetOf<PsiNamedElement>()

    fun resolve(): Set<ClassMetadata>? {
        val name = clazz.name
        val classEvaluator = ClassEvaluator.create(clazz) ?: return null
        val fields = classEvaluator.getFields()

    }

    private fun resolveClass(clazz: PsiNamedElement) {
        if (resolvedClasses.add(clazz)) {

        }
    }

}