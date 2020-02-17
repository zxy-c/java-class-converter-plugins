package com.zxy.ijplugin.javaToTypescript.actions

import com.intellij.ide.PsiCopyPasteManager
import com.intellij.lang.javascript.psi.JSFile
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.codeStyle.CodeStyleManager
import org.jetbrains.kotlin.idea.util.application.runWriteAction

class ClassToTypescriptFileAtClipboardAction : AbstractClassToTypescriptFileAction() {

    override fun processFile(jsFile: JSFile, e: AnActionEvent) {
        WriteCommandAction.runWriteCommandAction(jsFile.project){
            CodeStyleManager.getInstance(jsFile.project).reformat(jsFile)
        }
        PsiCopyPasteManager.getInstance().setElements(arrayOf(jsFile), true)
    }
}