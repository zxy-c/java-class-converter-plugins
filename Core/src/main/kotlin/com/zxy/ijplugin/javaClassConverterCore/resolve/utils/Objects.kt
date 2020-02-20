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

package com.zxy.ijplugin.javaClassConverterCore.resolve.utils

enum class ObviousType {
    STRING,
    ARRAY,
    OBJECT,
    BOOLEAN,
    ANY,
    ENUM,
    INT,
    DOUBLE,
    LONG,
    FLOAT,
    MAP,
    COLLECTION
}

class ClassMetadata(
    val name: String,
    val properties: Set<ClassPropertyMetadata>,
    val doc: String?,
    val isEnum: Boolean,
    val enums: Set<EnumEntryMetadata>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClassMetadata

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

class EnumEntryMetadata(
    val name: String,
    val doc: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EnumEntryMetadata

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

class ClassPropertyMetadata(
    val name: String,
    val type: TypeMetadata,
    val isNullable: Boolean?,
    val isFinal: Boolean,
    val doc: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClassPropertyMetadata

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

class TypeMetadata(
    val name: String,
    val type: ObviousType?,
    val types: List<TypeMetadata>
)

