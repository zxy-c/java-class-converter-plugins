package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.TokenType
import com.intellij.psi.javadoc.PsiDocComment

internal fun PsiDocComment.getContent(): String {
    return this.descriptionElements.joinToString("") {
        if (it.node.elementType == TokenType.WHITE_SPACE) {
            it.text.replace(Regex(" +$"), "")
        } else {
            it.text.trim()
        }
    }.trim()
}