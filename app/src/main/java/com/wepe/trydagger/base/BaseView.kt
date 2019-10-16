package com.wepe.trydagger.base

interface BaseView {
    fun setupPresenter(presenter: BasePresenter<*>)
}