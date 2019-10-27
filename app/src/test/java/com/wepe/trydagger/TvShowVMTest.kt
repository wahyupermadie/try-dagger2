package com.wepe.trydagger

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.data.database.TvShowDao
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
    private val tvShowDao : TvShowDao = mock(TvShowDao::class.java)
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        testDispatcher = TestCoroutineProvider()
        viewModel = TvShowViewModel(domain, testDispatcher, tvShowDao)
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

    @Test
    fun getMovies_local() {
        runBlocking {
            tvLiveData.value = Resource(Resource.Status.SUCCESS, FAKE_TV, "")
            launch(testDispatcher.uiThread()) {

                doReturn(tvLiveData)
                    .`when`(domain)
                    .fetchTvShowLocal()

            }
        }
        viewModel.tvPaged
        assertNotNull(tvLiveData)
        assertEquals(FAKE_TV, tvLiveData.value?.data)
    }

    @Test
    fun getMovies_singleLocal() {
        runBlocking {
            tvLiveData.value = Resource(Resource.Status.SUCCESS, FAKE_TV, "")
            launch(testDispatcher.uiThread()) {

                doReturn(tvLiveData)
                    .`when`(domain)
                    .fetchSingleLocalTvShow(1)

            }
        }
        viewModel.getTvShow(1)
        assertNotNull(tvLiveData)
        assertEquals(FAKE_TV, tvLiveData.value?.data)
    }
}