package com.dimanupy.notes.shared.domain

abstract class ValueObject<T>(private val _value: T) {
    init {
        validate(_value)
    }

    protected abstract fun validate(value: T)

    val value: T
        get() = _value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValueObject<*>) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int = value?.hashCode() ?: 0
}
