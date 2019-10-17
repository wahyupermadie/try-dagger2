package com.wepe.trydagger.external

import kotlinx.coroutines.Dispatchers

class AppContextCoroutineProvider : ContextCoroutineProvider {
    override fun uiDispatcher() = Dispatchers.Main
    override fun bgDispatcher() = Dispatchers.IO
}