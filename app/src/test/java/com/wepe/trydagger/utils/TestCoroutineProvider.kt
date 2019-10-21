package com.wepe.trydagger.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestCoroutineProvider : CoroutinesContextProvider{
    override fun uiThread(): CoroutineContext {
        return Dispatchers.Unconfined
    }

    override fun bgThread(): CoroutineContext {
        return Dispatchers.Unconfined
    }
}