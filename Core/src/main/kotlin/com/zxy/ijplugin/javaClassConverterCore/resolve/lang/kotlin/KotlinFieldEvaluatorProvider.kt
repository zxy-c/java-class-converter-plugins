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

package com.zxy.ijplugin.javaClassConverterCore.resolve.lang.kotlin

import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.javaClassConverterCore.resolve.FieldEvaluator
import com.zxy.ijplugin.javaClassConverterCore.resolve.FieldEvaluatorProvider
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtProperty

class KotlinFieldEvaluatorProvider : FieldEvaluatorProvider {
    override fun create(field: PsiNamedElement): FieldEvaluator<*, *, *>? {
        return if ((field is KtProperty || field is KtParameter) && field is KtNamedDeclaration) {
            KotlinFieldEvaluator(field)
        } else if (field is KtEnumEntry) {
            KotlinFieldEvaluator(field)
        } else {
            null
        }
    }
}