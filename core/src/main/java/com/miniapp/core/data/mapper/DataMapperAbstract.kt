package com.miniapp.core.data.mapper

abstract class DataMapperAbstract<in T, out R> {
    abstract fun map(data: T): R
}