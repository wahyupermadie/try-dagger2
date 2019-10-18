package com.wepe.trydagger

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.resource.ResponseFakeMovies
import com.wepe.trydagger.ui.tv.fragment.TvShowContract
import com.wepe.trydagger.ui.tv.fragment.TvShowPresenter
import com.wepe.trydagger.utils.Resource
import com.wepe.trydagger.utils.TestContextCoroutineProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class TvTest {

    @get:Rule
    open var instantTaskExecutorRule = InstantTaskExecutorRule()
    private var tvView = mock<TvShowContract.View>()
    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var presenter : TvShowPresenter
    private var tvDomain = mock<TvDomain>()
    private var tvLiveData: MutableLiveData<Resource<ResponseTv>> = MutableLiveData()
    private lateinit var testDispatcher: TestContextCoroutineProvider

    @Before
    fun setUp(){
        testDispatcher = TestContextCoroutineProvider()
        presenter = TvShowPresenter(tvDomain, testDispatcher)
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
        tvLiveData.value = Resource(Resource.Status.SUCCESS, ResponseFakeMovies.FAKE_TV, "")
        runBlocking {
            tvDomain.fetchTv(1, "123456")
            `when`(tvDomain.fetchTv(1, "123456"))
                .thenReturn(tvLiveData)
        }

        presenter.fetchTvShow(1, "123456")
        assertNotNull(tvLiveData.value)
        verify(tvView).showProgressBar(true)
        tvLiveData.value?.data?.let { verify(tvView).onTvShowSuccess(it) }
        verify(tvView).showProgressBar(false)
        assertEquals(Resource.Status.SUCCESS, tvLiveData.value?.status)
    }

    @Test
    fun `load data error`() {
        tvLiveData.value = Resource(
            status = Resource.Status.ERROR,
            data = null,
            error = "Connection Error"
        )
        runBlocking {
            tvDomain.fetchTv(1, "123456")
            doReturn(tvLiveData)
                .`when`(tvDomain)
                .fetchTv(1, "123456")
        }


        assertNotNull(tvLiveData)
        presenter.fetchTvShow(1, "123456")
        verify(tvView).showProgressBar(true)
        verify(tvView).showError(tvLiveData.value?.error)
        verify(tvView).showProgressBar(false)
        assertEquals("Connection Error", tvLiveData.value?.error)
    }
}