package com.zxy.ijplugin.psiEvaluator.resolve.lang.kotlin

import com.zxy.ijplugin.psiEvaluator.resolve.AbstractDocResolver
import org.jetbrains.kotlin.kdoc.psi.api.KDoc

class KotlinDocResolver(docElement: KDoc) : AbstractDocResolver<KDoc>(docElement) {
    private val defaultDocSectionResolver = KotlinDefaultDocSectionResolver(docElement.getDefaultSection())

    override fun getDocContent(): String? {
        return this.defaultDocSectionResolver.getDocContent()
    }


}