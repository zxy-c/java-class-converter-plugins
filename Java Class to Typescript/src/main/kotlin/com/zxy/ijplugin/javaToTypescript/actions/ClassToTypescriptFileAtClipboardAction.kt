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