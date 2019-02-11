package io.github.kobakei.katsuo.presentation.common

import android.app.Activity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class NonNullExtra<out T> : ReadOnlyProperty<Activity, T> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Activity, property: KProperty<*>): T =
        requireNotNull(thisRef.intent.extras).get(property.name) as T
}

class NullableExtra<out T> : ReadOnlyProperty<Activity, T?> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Activity, property: KProperty<*>): T? =
        thisRef.intent.extras?.get(property.name) as? T
}