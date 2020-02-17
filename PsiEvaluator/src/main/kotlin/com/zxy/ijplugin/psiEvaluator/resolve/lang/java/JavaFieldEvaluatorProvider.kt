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

import com.intellij.psi.PsiField
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.FieldEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.FieldEvaluatorProvider

class JavaFieldEvaluatorProvider : FieldEvaluatorProvider {
    override fun create(field: PsiNamedElement): FieldEvaluator<*, *, *>? {
        return if (field is PsiField) {
            JavaFieldEvaluator(field)
        } else {
            null
        }
    }

}