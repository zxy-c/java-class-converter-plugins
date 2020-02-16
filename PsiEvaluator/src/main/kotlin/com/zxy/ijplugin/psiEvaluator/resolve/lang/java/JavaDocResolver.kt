package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.javadoc.PsiDocComment
import com.intellij.psi.javadoc.PsiDocTag
import com.zxy.ijplugin.psiEvaluator.resolve.AbstractDocResolver

class JavaDocResolver(docElement: PsiDocComment) : AbstractDocResolver<PsiDocComment>(docElement) {


    override fun getDocContent(): String? {
        return this.docElement.getContent()
    }

}

fun PsiDocTag.getContent(): String {
    return this.dataElements.joinToString("") { it.text }.trim()
}