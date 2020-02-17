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

import com.intellij.lang.javascript.TypeScriptFileType
import com.intellij.lang.javascript.psi.JSFile
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiFileFactory

abstract class AbstractClassToTypescriptFileAction : AbstractClassToTypescriptAction() {
    final override fun processText(text: String, name: String, e: AnActionEvent) {
        val project = e.getData(CommonDataKeys.PROJECT) ?: return
        val jsFile = PsiFileFactory.getInstance(project).createFileFromText(
            "${name}.ts",
            TypeScriptFileType.INSTANCE.language,
            text
        ) as JSFile
        this.processFile(jsFile, e)
    }

    abstract fun processFile(jsFile: JSFile, e: AnActionEvent)
}