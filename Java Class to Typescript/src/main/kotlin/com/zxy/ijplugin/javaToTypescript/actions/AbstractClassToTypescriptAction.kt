package com.zxy.ijplugin.javaToTypescript.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.util.PsiTreeUtil
import com.zxy.ijplugin.psiEvaluator.resolve.ClassEvaluator
import com.zxy.ijplugin.javaToTypescript.utils.ToTypescriptUtils

abstract class AbstractClassToTypescriptAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val psiElement = findPsiElement(e) ?: return
        val clazz = this.findClass(psiElement) ?: return
        ToTypescriptUtils.convertClass(clazz)?.let {
            this.processText(it, clazz.name!!, e)
        }
    }

    override fun update(e: AnActionEvent) {
        val psiElement = findPsiElement(e)
        e.presentation.isEnabledAndVisible =
            psiElement != null && this.findClass(psiElement) != null
    }

    private fun findPsiElement(e: AnActionEvent): PsiElement? {
        val editor = e.getData(PlatformDataKeys.EDITOR)
        val psiFile = e.getData(PlatformDataKeys.PSI_FILE)
        return if (editor != null && psiFile != null) {
            psiFile.findElementAt(editor.caretModel.offset)
        } else {
            null
        }
    }

    private fun findClass(psiElement: PsiElement): PsiNamedElement? {
        if (isClass(psiElement)) {
            return psiElement as PsiNamedElement
        }
        return PsiTreeUtil.findFirstParent(psiElement, this::isClass) as? PsiNamedElement
    }

    private fun isClass(psiElement: PsiElement): Boolean {
        return ClassEvaluator.create(psiElement) != null
    }

    abstract fun processText(text: String, name: String, e: AnActionEvent)
}