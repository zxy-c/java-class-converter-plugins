package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiArrayType
import com.intellij.psi.PsiTypeElement
import com.intellij.psi.PsiTypeParameter
import com.zxy.ijplugin.psiEvaluator.resolve.utils.findChildOfType

/**
 * java的类型元素
 */
class JavaTypeElementEvaluator(typeElement: PsiTypeElement) : AbstractJavaTypeEvaluator<PsiTypeElement>(
    typeElement
) {

    override val psiType = this.type.type

    override fun getTypeParameters(): Array<out PsiTypeElement>? {
        if (this.psiType is PsiArrayType){
            val psiTypeElement = this.type.findChildOfType<PsiTypeElement>()
            return if (psiTypeElement==null){
                emptyArray()
            }else{
                arrayOf(psiTypeElement)
            }
        }
        return this.type.innermostComponentReferenceElement?.parameterList?.typeParameterElements
    }

    override fun getAnnotations(): Array<out PsiAnnotation> {
        return this.type.annotations
    }

    override fun getOriginType(): PsiTypeParameter? {
        return this.type.innermostComponentReferenceElement?.resolve() as? PsiTypeParameter
    }

    override fun getName(): String? {
        return this.type.innermostComponentReferenceElement?.firstChild?.text
    }
}