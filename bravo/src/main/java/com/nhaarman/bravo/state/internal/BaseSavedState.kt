package com.nhaarman.bravo.state.internal

import com.nhaarman.bravo.state.SavedState

/**
 * Provides a base implementation for the [SavedState] interface.
 */
internal class BaseSavedState(
    private val map: MutableMap<String, Any?> = mutableMapOf()
) : SavedState {

    override val entries: Set<Map.Entry<String, Any?>>
        get() {
            return map.entries
        }

    override fun set(key: String, value: Number?) {
        map[key] = value
    }

    override fun set(key: String, value: String?) {
        map[key] = value
    }

    override fun setUnchecked(key: String, value: Any?) {
        map[key] = value
    }

    private fun get(key: String) = map[key]

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(key: String, default: T?): T? {
        return get(key) as? T ?: default
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseSavedState

        if (map != other.map) return false

        return true
    }

    override fun hashCode(): Int {
        return map.hashCode()
    }

    override fun toString(): String {
        return "$map"
    }
}