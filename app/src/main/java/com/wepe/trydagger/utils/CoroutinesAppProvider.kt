package com.wepe.trydagger.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutinesAppProvider : CoroutinesContextProvider{
    override fun uiThread(): CoroutineContext {
        return Dispatchers.Main
    }

    override fun bgThread(): CoroutineContext {
        return Dispatchers.IO
    }
}