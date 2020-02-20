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

package com.zxy.ijplugin.javaClassConverterCore.resolve.lang.kotlin

import com.zxy.ijplugin.javaClassConverterCore.resolve.AbstractClassEvaluator
import com.zxy.ijplugin.javaClassConverterCore.resolve.utils.instanceof
import org.jetbrains.kotlin.idea.refactoring.fqName.getKotlinFqName
import org.jetbrains.kotlin.idea.refactoring.memberInfo.qualifiedClassNameForRendering
import org.jetbrains.kotlin.kdoc.psi.api.KDoc
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*

class KotlinClassEvaluator(clazz: KtClass) :
    AbstractClassEvaluator<KtClass, KtNamedDeclaration, KDoc, KtTypeReference, KtTypeParameter>(
        clazz
    ) {

    override fun instanceof(fqName: String): Boolean {
        return this.clazz.instanceof(fqName)
    }

    /**
     * kotlin的class中可以从主构造方法中提取parameter
     * 也可以从body中提取property
     */
    override fun getFields(): Array<KtNamedDeclaration> {
        val fields = ArrayList<KtNamedDeclaration>()
        // kotlin 主构造函数中得val 或者 var
        this.clazz.primaryConstructor?.valueParameters?.filter { parameter ->
            (parameter.node.findChildByType(KtTokens.VAL_KEYWORD) != null || parameter.node.findChildByType(KtTokens.VAR_KEYWORD) != null) && !parameter.annotationEntries.any {
                it.getKotlinFqName() == FqName("kotlin.jvm.Transient")
            }
        }?.let {
            fields.addAll(it)
        }
        // kotlin 类body中的非私有属性
        this.clazz.body?.properties?.filter { property ->
            !property.annotationEntries.any {
                it.getKotlinFqName() == FqName("kotlin.jvm.Transient")
            }
        }?.let {
            fields.addAll(it)
        }
        return fields.toTypedArray()
    }

    override fun getDoc(): KDoc? {
        return this.clazz.docComment
    }

    override fun isEnum(): Boolean {
        return this.clazz.isEnum()
    }

    override fun isAny(): Boolean {
        return this.clazz.qualifiedClassNameForRendering() == "kotlin.Any"
    }

    override fun getSupperTypes(): Array<KtTypeReference> {
        return this.clazz.superTypeListEntries.filterIsInstance<KtSuperTypeCallEntry>().mapNotNull {
            it.calleeExpression.typeReference
        }.toTypedArray()
    }

    override fun getTypeParameters(): Array<out KtTypeParameter> {
        return this.clazz.typeParameters.toTypedArray()
    }
}