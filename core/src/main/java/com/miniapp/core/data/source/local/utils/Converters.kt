package com.miniapp.core.data.source.local.utils

import androidx.room.TypeConverter
import java.lang.StringBuilder

class Converters {
    @TypeConverter
    fun convertListIntToString(data: List<Int?>?): String? {
        return when (data) {
            null -> null
            else -> {
                val str = StringBuilder()
                data.forEachIndexed { index, i ->
                    if (data.isEmpty()) str.append("0")
                    else if (data.size == 1 || index == data.lastIndex) str.append("$i")
                    else str.append("$i,")
                }

                str.toString()
            }
        }
    }

    @TypeConverter
    fun convertStringToListInt(data: String?): List<Int?>? {
        return when (data) {
            null -> null
            else -> {
                val lst = mutableListOf<Int>()
                val dataSplit = data.split(",")
                when {
                    data.isEmpty() -> lst.add(0)
                    data.length == 1 -> lst.add(data.toInt())
                    else -> dataSplit.forEach { lst.add(it.toInt()) }
                }

                lst.toList()
            }
        }
    }

    @TypeConverter
    fun convertListStringToString(data: List<String?>?): String? {
        return when (data) {
            null -> null
            else -> {
                val str = StringBuilder()
                data.forEachIndexed { index, i ->
                    if (data.size == 1 || index == data.lastIndex) str.append(i)
                    else str.append("$i,")
                }

                str.toString()
            }
        }
    }

    @TypeConverter
    fun convertStringToListString(data: String?): List<String?>? {
        return when (data) {
            null -> null
            else -> {
                val lst = mutableListOf<String>()
                val dataSplit = data.split(",")
                if (data.length == 1) lst.add(data)
                else dataSplit.forEach { lst.add(it) }

                lst.toList()
            }
        }
    }
}