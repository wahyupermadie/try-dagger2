package com.wepe.trydagger.base

import android.view.View

interface IBasePresenter<T : BaseView> {
    fun onAttachView(view: T)
}