package com.zxy.ijplugin.javaToTypescript.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.StringSelection

class ClassToTypescriptInterfaceAtClipboardAction : AbstractClassToTypescriptAction() {

    override fun processText(text: String, name: String, e: AnActionEvent) {
        CopyPasteManager.getInstance().setContents(StringSelection(text))
    }

}