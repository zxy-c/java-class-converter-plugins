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