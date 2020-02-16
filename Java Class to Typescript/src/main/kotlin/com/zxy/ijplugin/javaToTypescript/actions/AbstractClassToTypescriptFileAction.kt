package com.zxy.ijplugin.javaToTypescript.actions

import com.intellij.lang.javascript.TypeScriptFileType
import com.intellij.lang.javascript.psi.JSFile
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.codeStyle.CodeStyleManager

abstract class AbstractClassToTypescriptFileAction : AbstractClassToTypescriptAction() {
    final override fun processText(text: String, name: String, e: AnActionEvent) {
        val project = e.getData(CommonDataKeys.PROJECT) ?: return
        val jsFile = PsiFileFactory.getInstance(project).createFileFromText(
            "${name}.ts",
            TypeScriptFileType.INSTANCE.language,
            text
        ) as JSFile
        CodeStyleManager.getInstance(project).reformat(jsFile)
        this.processFile(jsFile, e)
    }

    abstract fun processFile(jsFile: JSFile, e: AnActionEvent)
}