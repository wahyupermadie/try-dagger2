package com.wepe.trydagger

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.resource.ResponseFakeMovies
import com.wepe.trydagger.ui.tv.fragment.TvShowContract
import com.wepe.trydagger.ui.tv.fragment.TvShowPresenter
import com.wepe.trydagger.utils.Resource
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class TvTest {

    @get:Rule
    open var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var tvView = mockk<TvShowContract.View>()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private var presenter = mockk<TvShowPresenter>(relaxed = true)

    private var tvDomain = mockk<TvDomain>(relaxed = true)

    private var tvLiveData: MutableLiveData<Resource<ResponseTv>> = MutableLiveData()

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        presenter.onAttachView(tvView)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun tearDown(){
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `load data movies`() {
        every{
            runBlocking {
                tvDomain.fetchTv(1, "123456")
            }
        } returns tvLiveData

        tvLiveData.value = Resource(Resource.Status.SUCCESS, ResponseFakeMovies.FAKE_TV, "")
        assertNotNull(tvLiveData.value)
        assertEquals(Resource.Status.SUCCESS, tvLiveData.value?.status)
    }

    @Test
    fun `load data error`() {
        val exception = NetworkErrorException()
        tvLiveData.value = Resource(
            status = Resource.Status.ERROR,
            data = null,
            error = "Connection Error"
        )
        every {
            runBlocking {
                tvDomain.fetchTv(1, "123456")
            }
        } throws exception andThen {
            tvLiveData
        }


        assertNotNull(tvLiveData)
        assertEquals("Connection Error", tvLiveData.value?.error)
    }
}