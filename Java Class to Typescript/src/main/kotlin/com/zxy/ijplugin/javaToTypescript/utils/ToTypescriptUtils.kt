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

package com.zxy.ijplugin.javaToTypescript.utils

import com.intellij.psi.PsiNamedElement
import com.zxy.ijplugin.javaClassConverterCore.resolve.utils.*

object ToTypescriptUtils {

    fun convertClass(clazz: PsiNamedElement): String? {
        val classes = ClassMetadataResolver.resolve(clazz)
        if (classes.isEmpty()) return null
        return classes.joinToString("\n\n") {
            val classStringBuilder = StringBuilder()
            this.appendDoc(classStringBuilder, it.doc)
            if (it.isEnum) {
                classStringBuilder.append("enum ${it.name}{\n")
                classStringBuilder.append(it.enums.mapNotNull(this::convertEnumEntry).joinToString(",\n"))
                classStringBuilder.append("\n}")
            } else {
                classStringBuilder.append("interface ${it.name}{\n")
                classStringBuilder.append(it.properties.mapNotNull(this::convertField).joinToString("\n"))
                classStringBuilder.append("\n}")
            }
            classStringBuilder.toString()
        }
    }

    private fun convertField(field: ClassPropertyMetadata): String? {
        val fieldString = StringBuilder()
        this.appendDoc(fieldString, field.doc)
        val type = field.type
        val name = field.name
        val typeSection = convertType(type) ?: return null
        if (field.isFinal) {
            fieldString.append("readonly ")
        }
        fieldString.append(name)
        fieldString.append(
            if (field.isNullable == true) {
                "?:"
            } else ":"
        )
        fieldString.append(typeSection)
        fieldString.append(";")
        return fieldString.toString()
    }

    private fun convertType(typeMetadata: TypeMetadata): String? {
        return when (typeMetadata.type) {
            ObviousType.ARRAY, ObviousType.COLLECTION -> {
                val firstType = typeMetadata.types.firstOrNull() ?: return null
                val typeString = this.convertType(firstType) ?: return null
                "Array<$typeString>"
            }
            ObviousType.DOUBLE, ObviousType.FLOAT,
            ObviousType.INT, ObviousType.LONG -> {
                "number"
            }
            ObviousType.BOOLEAN -> {
                "boolean"
            }
            ObviousType.STRING -> {
                "string"
            }
            ObviousType.MAP -> {
                val types = typeMetadata.types.take(2).mapNotNull { this.convertType(it) }
                if (types.size != 2 && types.first() == "string" || types.first() == "number") return null
                "{\n[key: ${types.first()}]:${types[1]}\n}"
            }
            ObviousType.ANY -> {
                "any"
            }
            else -> {
                val types = typeMetadata.types.map { this.convertType(it) }
                if (types.any { it == null }) {
                    return null
                }
                val typesString = if (types.isNotEmpty()) {
                    "<${types.joinToString(", ")}>"
                } else {
                    ""
                }
                "${typeMetadata.name}$typesString"
            }
        }
    }

    private fun convertEnumEntry(enumEntryMetadata: EnumEntryMetadata): String? {
        val enumEntryStringBuilder = StringBuilder()
        this.appendDoc(enumEntryStringBuilder, enumEntryMetadata.doc)
        enumEntryStringBuilder.append(enumEntryMetadata.name)
        return enumEntryStringBuilder.toString()
    }

    private fun appendDoc(stringBuilder: StringBuilder, doc: String?) {
        doc?.let {
            stringBuilder.append(
                """
                /*
                 * 
            """.trimIndent()
            )
            stringBuilder.append(doc.split("\n").joinToString("\n * "))
            stringBuilder.append("\n */")
            stringBuilder.append("\n")
        }
    }

}