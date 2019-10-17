package com.wepe.trydagger.utils

import com.wepe.trydagger.external.ContextCoroutineProvider
import kotlinx.coroutines.Dispatchers

class TestContextCoroutineProvider : ContextCoroutineProvider {
    override fun uiDispatcher() = Dispatchers.Unconfined
    override fun bgDispatcher() = Dispatchers.Unconfined
}