package com.wepe.trydagger

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.resource.ResponseFakeMovies.FAKE_MOVIES
import com.wepe.trydagger.ui.movies.fragment.MoviesContract
import com.wepe.trydagger.ui.movies.fragment.MoviesPresenter
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
class MoviesTest {

    @get:Rule
    open var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var moviesView = mockk<MoviesContract.View>()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private var presenter = mockk<MoviesPresenter>(relaxed = true)

    private var moviesDomain = mockk<MoviesDomain>(relaxed = true)

    private var moviesLiveData: MutableLiveData<Resource<ResponseMovies>> = MutableLiveData()

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        presenter.onAttachView(moviesView)
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
                moviesDomain.fetchMovies(1, "123456")
            }
        } returns moviesLiveData

        moviesLiveData.value = Resource(Resource.Status.SUCCESS, FAKE_MOVIES, "")
        assertNotNull(moviesLiveData.value)
        assertEquals(Resource.Status.SUCCESS, moviesLiveData.value?.status)
    }

    @Test
    fun `load data error`() {
        val exception = NetworkErrorException()
        moviesLiveData.value = Resource(
            status = Resource.Status.ERROR,
            data = null,
            error = "Connection Error"
        )
        every {
            runBlocking {
                moviesDomain.fetchMovies(1, "123456")
            }
        } throws exception andThen {
            moviesLiveData
        }


        assertNotNull(moviesLiveData)
        assertEquals("Connection Error", moviesLiveData.value?.error)
    }
}