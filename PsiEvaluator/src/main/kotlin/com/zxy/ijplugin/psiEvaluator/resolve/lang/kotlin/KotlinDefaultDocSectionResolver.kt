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

import com.zxy.ijplugin.psiEvaluator.resolve.AbstractDocResolver
import org.jetbrains.kotlin.kdoc.psi.impl.KDocSection

/**
 * KDoc的section概念尚未明了
 * 只需关心default section即可
 */
class KotlinDefaultDocSectionResolver(docElement: KDocSection) : AbstractDocResolver<KDocSection>(docElement) {

    override fun getDocContent(): String? {
        return this.docElement.getContent()
    }


}