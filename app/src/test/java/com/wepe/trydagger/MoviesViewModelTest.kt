package com.wepe.trydagger

import android.os.Build
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.wepe.trydagger.resource.ResponseMovies.FAKE_MOVIES
import com.wepe.trydagger.ui.movies.module.MoviesModule
import com.wepe.trydagger.ui.movies.viewmodel.MoviesViewModel
import com.wepe.trydagger.viewmodeltest.base.BaseTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.HttpURLConnection
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MoviesViewModelTest : BaseTest(){
    // FOR DATA
    private lateinit var activity: FragmentActivity
    private lateinit var viewModel: MoviesViewModel

    override fun isMockServerEnabled(): Boolean = true

    override fun getRepoModule(): Any = MoviesModule::class

    @Before
    override fun setUp(){
        super.setUp()
        this.activity = Robolectric.setupActivity(FragmentActivity::class.java)
        this.viewModel = ViewModelProviders.of(this.activity, viewModelFactory)[MoviesViewModel::class.java]
    }

    // TESTS
    @Test
    fun getUser_whenSuccess() {
        // Prepare data
        this.mockHttpResponse("movies_data.json", HttpURLConnection.HTTP_OK)
        this.viewModel.getMovies(1)
        // Pre-test
        assertEquals(null, this.viewModel.movies.value, "User should be null because stream not started yet")
        // Execute View Model
        this.viewModel.getMovies(1)
        // Checks
        assertEquals(FAKE_MOVIES, this.viewModel.movies.value, "User must be fetched")
    }

}