package com.wepe.trydagger

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.ui.movies.module.MoviesModule
import com.wepe.trydagger.ui.movies.viewmodel.MoviesViewModel
import com.wepe.trydagger.utils.Resource
import com.wepe.trydagger.utils.ResponseFakeMovies.FAKE_MOVIES
import com.wepe.trydagger.utils.TestCoroutineProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var testDispatcher: TestCoroutineProvider
    private val domain : MoviesDomain = mock(MoviesDomain::class.java)
    private var moviesLiveData = MutableLiveData<Resource<ResponseMovies>>()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        testDispatcher = TestCoroutineProvider()
        viewModel = MoviesViewModel(domain, testDispatcher)
    }

    // TESTS
    @Test
    fun getMovies_whenSuccess() {
        runBlocking {
            moviesLiveData.value = Resource(Resource.Status.SUCCESS, FAKE_MOVIES, "")
            launch(testDispatcher.uiThread()) {

                doReturn(moviesLiveData)
                    .`when`(domain)
                    .fetchMovies(1, BuildConfig.API_KEY)

            }
        }
        viewModel.getMovies(1)
        assertNotNull(moviesLiveData)
        assertEquals(FAKE_MOVIES, moviesLiveData.value?.data)
    }

    // TESTS
    @Test
    fun getMovies_whenError() {
        runBlocking {
            moviesLiveData.value = Resource(Resource.Status.ERROR, null, "Connection Error")
            print(moviesLiveData.value?.data)
            launch(testDispatcher.uiThread()) {

                doReturn(moviesLiveData)
                    .`when`(domain)
                    .fetchMovies(1, BuildConfig.API_KEY)

            }
        }
        viewModel.getMovies(1)
        assertNotNull(moviesLiveData)
        assertEquals(null, moviesLiveData.value?.data)
        assertEquals("Connection Error", moviesLiveData.value?.error)
    }
}