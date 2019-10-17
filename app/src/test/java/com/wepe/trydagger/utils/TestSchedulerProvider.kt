package com.wepe.trydagger.utils

import com.wepe.trydagger.external.ContextCoroutineProvider
import kotlinx.coroutines.Dispatchers

/**
 * Created by ibnumuzzakkir on 09/12/17.
 * Android Engineer
 * SCO Project
 */
class TestContextCoroutineProvider : ContextCoroutineProvider {
    override fun uiDispatcher() = Dispatchers.Unconfined
    override fun bgDispatcher() = Dispatchers.Unconfined
}