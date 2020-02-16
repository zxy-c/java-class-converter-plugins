package com.zxy.ijplugin.javaToTypescript.actions

import com.intellij.ide.PsiCopyPasteManager
import com.intellij.lang.javascript.psi.JSFile
import com.intellij.openapi.actionSystem.AnActionEvent

class ClassToTypescriptFileAtClipboardAction : AbstractClassToTypescriptFileAction() {

    override fun processFile(jsFile: JSFile, e: AnActionEvent) {
        PsiCopyPasteManager.getInstance().setElements(arrayOf(jsFile), true)
    }
}