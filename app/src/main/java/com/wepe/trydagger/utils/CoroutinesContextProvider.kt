package com.wepe.trydagger.utils

import kotlin.coroutines.CoroutineContext

interface CoroutinesContextProvider{
    fun uiThread() : CoroutineContext
    fun bgThread() : CoroutineContext
}