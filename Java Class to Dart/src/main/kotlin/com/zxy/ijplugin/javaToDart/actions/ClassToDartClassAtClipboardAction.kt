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

package com.zxy.ijplugin.javaToDart.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.StringSelection

class ClassToDartClassAtClipboardAction : AbstractClassToDartAction() {

    override fun processText(text: String, name: String, e: AnActionEvent) {
        CopyPasteManager.getInstance().setContents(StringSelection(text))
    }

}