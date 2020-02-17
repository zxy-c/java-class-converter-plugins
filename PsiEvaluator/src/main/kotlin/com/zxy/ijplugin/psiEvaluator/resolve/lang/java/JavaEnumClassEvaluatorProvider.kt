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

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.EnumClassEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.EnumClassEvaluatorProvider

class JavaEnumClassEvaluatorProvider : EnumClassEvaluatorProvider {
    override fun create(psiNamedElement: PsiNamedElement): EnumClassEvaluator<*, *>? {
        return if (psiNamedElement is PsiClass) {
            JavaEnumClassEvaluator(psiNamedElement)
        } else {
            null
        }
    }
}