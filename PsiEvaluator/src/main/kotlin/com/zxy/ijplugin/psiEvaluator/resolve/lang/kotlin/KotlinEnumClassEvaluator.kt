package com.zxy.ijplugin.psiEvaluator.resolve.lang.kotlin

import com.zxy.ijplugin.psiEvaluator.resolve.AbstractEnumClassEvaluator
import org.jetbrains.kotlin.kdoc.psi.api.KDoc
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtEnumEntry

class KotlinEnumClassEvaluator(enumClass: KtClass) : AbstractEnumClassEvaluator<KtClass, KtEnumEntry, KDoc>(
    enumClass
) {

    override fun getFields(): Array<KtEnumEntry> {
        return this.enumClass.declarations.filterIsInstance<KtEnumEntry>().toTypedArray()
    }

    override fun getDoc(): KDoc? {
        return this.enumClass.docComment
    }

}