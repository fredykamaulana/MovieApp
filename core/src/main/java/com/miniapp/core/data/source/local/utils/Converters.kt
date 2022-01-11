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
                    if (data.size == 1 || index == data.lastIndex) str.append("$i")
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
                if (data.length == 1) lst.add(data.toInt())
                else dataSplit.forEach { lst.add(it.toInt()) }

                lst.toList()
            }
        }
    }
}