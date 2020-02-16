package com.zxy.ijplugin.psiEvaluator.resolve.lang.java

import com.intellij.psi.*
import com.intellij.psi.javadoc.PsiDocComment
import com.zxy.ijplugin.psiEvaluator.resolve.AbstractClassEvaluator

class JavaClassEvaluator(clazz: PsiClass) :
    AbstractClassEvaluator<PsiClass, PsiField, PsiDocComment, PsiJavaCodeReferenceElement, PsiTypeParameter>(
        clazz
    ) {
    override fun instanceof(fqName: String): Boolean {
        return this.clazz.qualifiedName == fqName || PsiElementFactory.getInstance(this.clazz.project).createTypeByFQClassName(
            fqName
        ).resolve()?.let {
            this.clazz.isInheritor(it, true)
        } == true
    }

    override fun getFields(): Array<PsiField> {
        return this.clazz.fields.filter {
            // no static
            it.modifierList?.hasModifierProperty(PsiModifier.STATIC) != true
                    &&
                    it.modifierList?.hasModifierProperty(PsiModifier.TRANSIENT) != true
        }.toTypedArray()
    }

    override fun getDoc(): PsiDocComment? {
        return this.clazz.docComment
    }

    override fun isEnum(): Boolean {
        return this.clazz.isEnum
    }

    override fun isAny(): Boolean {
        return this.clazz.qualifiedName == "java.lang.Object"
    }


    override fun getSupperTypes(): Array<out PsiJavaCodeReferenceElement> {
        val superTypes = ArrayList<PsiJavaCodeReferenceElement>()
        this.clazz.extendsList?.referenceElements?.let {
            superTypes.addAll(it)
        }
        this.clazz.implementsList?.referenceElements?.let {
            superTypes.addAll(it)
        }
        return superTypes.toTypedArray()
    }

    override fun getTypeParameters(): Array<out PsiTypeParameter> {
        return this.clazz.typeParameters
    }

}