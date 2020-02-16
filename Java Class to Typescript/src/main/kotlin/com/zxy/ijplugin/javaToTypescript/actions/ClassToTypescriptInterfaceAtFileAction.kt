package com.zxy.ijplugin.javaToTypescript.actions

import com.intellij.ide.util.DirectoryChooserUtil
import com.intellij.lang.javascript.psi.JSFile
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiManager

class ClassToTypescriptInterfaceAtFileAction : AbstractClassToTypescriptFileAction() {

    override fun processFile(jsFile: JSFile, e: AnActionEvent) {
        val project = e.getData(CommonDataKeys.PROJECT) ?: return
        val ideView = e.getData(LangDataKeys.IDE_VIEW) ?: return

        DirectoryChooserUtil.chooseDirectory(ProjectRootManager.getInstance(project).contentRoots.mapNotNull {
            PsiManager.getInstance(project).findDirectory(it)
        }.toTypedArray(), ideView.directories.firstOrNull(), project, null)?.let {
            runWriteAction {
                it.add(jsFile)
            }
        }
    }

}