package com.zxy.ijplugin.javaToTypescript.actions

import com.intellij.lang.javascript.psi.JSFile
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.psi.PsiManager

class ClassToTypescriptInterfaceAtFileAction : AbstractClassToTypescriptFileAction() {

    override fun processFile(jsFile: JSFile, e: AnActionEvent) {
        val project = e.getData(CommonDataKeys.PROJECT) ?: return
        val ideView = e.getData(LangDataKeys.IDE_VIEW) ?: return
        val directories = ideView.directories
        val defaultSelectedFile = if (directories.size == 1) directories[0] else null
        FileChooser.chooseFile(
            FileChooserDescriptor(false, true, false, false, false, false),
            project,
            defaultSelectedFile?.virtualFile
        )?.let {
            PsiManager.getInstance(project).findDirectory(it)
        }?.let {
            runWriteAction {
                it.add(jsFile)
            }
        }
    }

}