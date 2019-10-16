package com.wepe.trydagger.base

import android.view.View

open class BasePresenter <T: BaseView> : IBasePresenter<T>{
    var view : T? = null

    override fun onAttachView(view: T) {
        this.view = view
    }
}