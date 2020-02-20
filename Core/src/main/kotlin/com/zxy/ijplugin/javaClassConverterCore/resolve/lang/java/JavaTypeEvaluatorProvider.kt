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

package com.zxy.ijplugin.javaClassConverterCore.resolve.lang.java

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.PsiTypeElement
import com.zxy.ijplugin.javaClassConverterCore.resolve.TypeEvaluator
import com.zxy.ijplugin.javaClassConverterCore.resolve.TypeEvaluatorProvider

class JavaTypeEvaluatorProvider : TypeEvaluatorProvider {
    override fun create(type: PsiElement?): TypeEvaluator<*>? {
        return when (type) {
            is PsiTypeElement -> {
                JavaTypeElementEvaluator(type)
            }
            is PsiJavaCodeReferenceElement -> {
                JavaCodeReferenceElementTypeEvaluator(type)
            }
            else -> {
                null
            }
        }
    }
}