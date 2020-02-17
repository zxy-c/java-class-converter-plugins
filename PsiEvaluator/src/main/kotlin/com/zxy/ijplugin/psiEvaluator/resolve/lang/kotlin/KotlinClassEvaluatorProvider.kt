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

import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.psiEvaluator.resolve.ClassEvaluator
import com.zxy.ijplugin.psiEvaluator.resolve.ClassEvaluatorProvider
import org.jetbrains.kotlin.asJava.classes.KtLightClass
import org.jetbrains.kotlin.psi.KtClass

class KotlinClassEvaluatorProvider : ClassEvaluatorProvider {
    override fun create(psiNamedElement: PsiNamedElement): ClassEvaluator<*, *, *, *>? {
        return when (psiNamedElement) {
            is KtLightClass -> {
                (psiNamedElement.navigationElement as? KtClass)?.let {
                    KotlinClassEvaluator(it)
                }
            }
            is KtClass -> {
                KotlinClassEvaluator(psiNamedElement)
            }
            else -> null
        }
    }

}