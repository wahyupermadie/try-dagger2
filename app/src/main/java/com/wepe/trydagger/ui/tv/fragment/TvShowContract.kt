package com.wepe.trydagger.ui.tv.fragment

import com.wepe.trydagger.base.BasePresenter
import com.wepe.trydagger.base.BaseView
import com.wepe.trydagger.data.model.ResponseTv
import kotlinx.coroutines.Job

interface TvShowContract {
    interface View : BaseView{
        fun onTvShowSuccess(responseTv: ResponseTv)
    }

    interface Presenter {
        fun fetchTvShow(page: Int, apiKey: String) : Job
    }
}