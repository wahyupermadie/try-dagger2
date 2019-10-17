package com.wepe.trydagger

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.resource.ResponseFakeMovies.FAKE_MOVIES
import com.wepe.trydagger.ui.movies.fragment.MoviesContract
import com.wepe.trydagger.ui.movies.fragment.MoviesPresenter
import com.wepe.trydagger.utils.Resource
import com.wepe.trydagger.utils.TestContextCoroutineProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesTest {

    private var moviesView = mock<MoviesContract.View>()
    private var moviesDomain: MoviesDomain = mock()
    private lateinit var testDispatcher: TestContextCoroutineProvider
    private var moviesLiveData = MutableLiveData<Resource<ResponseMovies>>()
    lateinit var presenter: MoviesPresenter

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun `Setup`() {
        testDispatcher = TestContextCoroutineProvider()
        presenter = MoviesPresenter(moviesDomain, testDispatcher)
        presenter.onAttachView(moviesView)
    }

    @Test
    fun `Load data movies`() = runBlocking {
        moviesLiveData.value = Resource(Resource.Status.SUCCESS, FAKE_MOVIES, "")

        launch(testDispatcher.uiDispatcher()) {

            doReturn(moviesLiveData)
                .`when`(moviesDomain)
                .fetchMovies(1, "123456")

        }
        presenter.getMovies(1, "123456")

        verify(moviesView).showProgressBar(true)
        Assert.assertEquals(moviesDomain.fetchMovies(1, "123456"), moviesLiveData)
        Assert.assertEquals(Resource.Status.SUCCESS, moviesLiveData.value?.status)
        Assert.assertEquals(FAKE_MOVIES, moviesLiveData.value?.data)
    }
}