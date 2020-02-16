package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.PsiTypeParameter
import com.intellij.psi.impl.source.PsiClassReferenceType

/**
 * 类的类型参数
 */
class JavaCodeReferenceElementTypeEvaluator(type: PsiJavaCodeReferenceElement) :
    AbstractJavaTypeEvaluator<PsiJavaCodeReferenceElement>(
        type
    ) {

    override val psiType = PsiClassReferenceType(this.type, null)

    override fun getTypeParameters(): Array<out PsiJavaCodeReferenceElement>? {
        return this.type.parameterList?.typeParameterElements?.mapNotNull {
            it.innermostComponentReferenceElement
        }?.toTypedArray()
    }

    override fun getAnnotations(): Array<out PsiAnnotation> {
        return emptyArray()
    }

    override fun getOriginType(): PsiTypeParameter? {
        return this.type.resolve() as? PsiTypeParameter
    }

    override fun getName(): String? {
        return this.type.firstChild?.text
    }

}