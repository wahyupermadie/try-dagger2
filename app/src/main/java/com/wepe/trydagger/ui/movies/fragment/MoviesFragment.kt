package com.wepe.trydagger.ui.movies.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wepe.trydagger.BuildConfig
import com.wepe.trydagger.MainApplication
import com.wepe.trydagger.base.BaseFragment
import com.wepe.trydagger.base.BasePresenter
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.databinding.FragmentMoviesBinding
import com.wepe.trydagger.ui.movies.adapter.MoviesAdapter
import com.wepe.trydagger.ui.movies.detail.DetailMovieActivity
import com.wepe.trydagger.ui.movies.viewmodel.MoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class MoviesFragment : BaseFragment(), MoviesContract.View{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var moviePresenter: MoviesPresenter

    private lateinit var binding : FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var mAdapter: MoviesAdapter
    companion object {

        fun newInstance() : MoviesFragment {
            return MoviesFragment()
        }
    }

    override fun onAttach(context: Context) {
        (activity?.application as MainApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun setupPresenter(presenter: BasePresenter<*>) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUi()
        initData()
    }

    private fun initData() {
//        viewModel.getMovies(1)
//        viewModel.movies.observe(viewLifecycleOwner, Observer {
//            if (it != null){
//                it.results?.forEach {results ->
//                    mAdapter.addData(results)
//                }
//                mAdapter.notifyDataSetChanged()
//            }
//        })
    }

    private fun initUi() {
//        mAdapter = MoviesAdapter(arrayListOf()){
//            startActivity<DetailMovieActivity>("movies" to it)
//        }
//
//        binding.rvMovies.apply {
//            this.adapter = mAdapter
//            this.layoutManager = LinearLayoutManager(context)
//        }

        moviePresenter.getMovies(1, BuildConfig.API_KEY)


    }

    override fun onMoviesSuccess(responseMovies: ResponseMovies) {
        toast("Hello "+responseMovies.toString())
        Log.d(MoviesFragment::class.java.simpleName, responseMovies.toString())
    }

    override fun onMoviesError(message: String) {
        Log.d(MoviesFragment::class.java.simpleName, message)
    }

    override fun onMoviesLoading(loadingState: Boolean) {
        Log.d(MoviesFragment::class.java.simpleName, loadingState.toString())
    }
}