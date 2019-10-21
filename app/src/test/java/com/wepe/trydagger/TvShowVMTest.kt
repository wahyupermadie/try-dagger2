package com.wepe.trydagger

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.ui.tv.viewmodel.TvShowViewModel
import com.wepe.trydagger.utils.Resource
import com.wepe.trydagger.utils.ResponseFakeMovies.FAKE_TV
import com.wepe.trydagger.utils.TestCoroutineProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TvShowVMTest {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var testDispatcher: TestCoroutineProvider
    private val domain : TvDomain = mock(TvDomain::class.java)
    private var tvLiveData = MutableLiveData<Resource<ResponseTv>>()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        testDispatcher = TestCoroutineProvider()
        viewModel = TvShowViewModel(domain, testDispatcher)
    }

    // TESTS
    @Test
    fun getMovies_whenSuccess() {
        runBlocking {
            tvLiveData.value = Resource(Resource.Status.SUCCESS, FAKE_TV, "")
            launch(testDispatcher.uiThread()) {

                doReturn(tvLiveData)
                    .`when`(domain)
                    .fetchTv(1, BuildConfig.API_KEY)

            }
        }
        viewModel.getTvShow(1)
        assertNotNull(tvLiveData)
        assertEquals(FAKE_TV, tvLiveData.value?.data)
    }

    // TESTS
    @Test
    fun getMovies_whenError() {
        runBlocking {
            tvLiveData.value = Resource(Resource.Status.ERROR, null, "Connection Error")
            print(tvLiveData.value?.data)
            launch(testDispatcher.uiThread()) {

                doReturn(tvLiveData)
                    .`when`(domain)
                    .fetchTv(1, BuildConfig.API_KEY)

            }
        }
        viewModel.getTvShow(1)
        assertNotNull(tvLiveData)
        assertEquals(null, tvLiveData.value?.data)
        assertEquals("Connection Error", tvLiveData.value?.error)
    }
}