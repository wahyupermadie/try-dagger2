package com.wepe.trydagger.viewmodeltest.base

import androidx.lifecycle.ViewModelProvider
import com.wepe.trydagger.di.module.NetworkModule
import com.wepe.trydagger.ui.movies.module.MoviesModule
import com.wepe.trydagger.viewmodeltest.di.TestAppComponent
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import java.io.File
import javax.inject.Inject

abstract class BaseTest {

    lateinit var testAppComponent: TestAppComponent
    lateinit var mockWebServer: MockWebServer
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Before
    open fun setUp(){
        this.configureMockServer()
        this.configureDI()
    }

    @After
    open fun tearDown(){
        this.stopMockServer()
    }

    // MOCK SERVER
    abstract fun isMockServerEnabled(): Boolean // Because we don't want it always enabled on all tests

    open fun configureMockServer(){
        if(isMockServerEnabled()){
            mockWebServer = MockWebServer()
            mockWebServer.start()
        }
    }

    open fun configureDI() {
        this.testAppComponent = DaggerTestAppComponent.builder()
            .netModule(NetworkModule())
            .repositoryModule(getRepoModule())
            .build()
        this.testAppComponent.inject(this)
    }

    abstract fun getRepoModule() : Any

    open fun stopMockServer(){
        if (isMockServerEnabled()){
            mockWebServer.shutdown()
        }
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) = mockWebServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    private fun getJson(fileName: String): String {
        val uri = this.javaClass.classLoader?.getResource(fileName)
        val file = uri?.let {
            File(it.path)
        }
        return String(file!!.readBytes())
    }
}