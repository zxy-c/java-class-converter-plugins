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

package com.zxy.ijplugin.javaClassConverterCore.resolve

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.psi.PsiElement

interface DocResolverProvider {
    companion object {
        @JvmField
        val EXTENSION_POINT =
            ExtensionPointName<DocResolverProvider>("com.zxy.ijplugin.javaClassConverterCore.docResolverProvider")
    }

    fun create(psiElement: PsiElement): DocResolver?
}

interface DocResolver {

    companion object {
        fun create(psiElement: PsiElement): DocResolver? {
            for (provider in DocResolverProvider.EXTENSION_POINT.extensions) {
                val resolver = provider.create(psiElement)
                if (resolver != null) {
                    return resolver
                }
            }
            return null
        }
    }

    fun getDocContent(): String?

}

/**
 * @param D Doc Element
 */
abstract class AbstractDocResolver<D : PsiElement>(protected val docElement: D) : DocResolver