package com.wepe.trydagger.base

interface BaseView  {
    fun showProgressBar(state: Boolean)
    fun showError(message: String?)
}