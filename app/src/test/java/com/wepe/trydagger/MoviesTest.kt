package com.wepe.trydagger

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.annotation.ContentView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.wepe.trydagger.base.BaseView
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.data.remote.MoviesRepositoryImpl
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.resource.ResponseFakeMovies
import com.wepe.trydagger.resource.ResponseFakeMovies.FAKE_MOVIES
import com.wepe.trydagger.ui.movies.fragment.MoviesContract
import com.wepe.trydagger.ui.movies.fragment.MoviesPresenter
import com.wepe.trydagger.utils.Resource
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.FileReader
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.impl.annotations.MockK
import org.junit.Rule


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
        every {
            runBlocking {
                moviesDomain.fetchMovies(1, "123456")
            }
        } throws exception andThen {
            moviesLiveData.value = Resource(
                status = Resource.Status.ERROR,
                data = null,
                error = "Connection Error"
            )
            moviesLiveData
        }

        assertNotNull(moviesLiveData)
        assertEquals("Connection Error", moviesLiveData.value?.error)
    }
}