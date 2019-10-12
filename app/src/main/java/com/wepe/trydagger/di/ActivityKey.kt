package com.wepe.trydagger.di

import android.app.Activity
import dagger.MapKey
import dagger.internal.Beta
import kotlin.reflect.KClass

@Beta
@MapKey
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class ActivityKey(val value: KClass<out Activity>)