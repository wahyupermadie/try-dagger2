package com.wepe.trydagger.base

interface IBasePresenter<T : BaseView> {
    fun onAttachView(view: T)
}