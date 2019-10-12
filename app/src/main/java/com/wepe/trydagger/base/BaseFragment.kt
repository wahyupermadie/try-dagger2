package com.wepe.trydagger.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.wepe.trydagger.extention.showSnackbar
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

abstract class BaseFragment : Fragment(){
    private lateinit var dialog : ProgressDialog

    abstract fun getViewModel() : BaseViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog = indeterminateProgressDialog("Fething data...", "Please wait")
        dialog.setCancelable(false)
        setupObserver(getViewModel())
    }

    private fun setupObserver(viewModel: BaseViewModel) {
        viewModel.errorHandler.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                showSnackbar(
                    it.message ?: "Terjadi kesalahan pada sistem",
                Snackbar.LENGTH_LONG)
            }
        })

        viewModel.loadingHandler.observe(viewLifecycleOwner, Observer {
            if (it){
                dialog.show()
            }else{
                dialog.dismiss()
            }
        })
    }
}