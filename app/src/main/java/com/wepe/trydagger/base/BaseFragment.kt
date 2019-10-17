package com.wepe.trydagger.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.wepe.trydagger.external.extention.showSnackbar
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

abstract class BaseFragment : Fragment(), BaseView{
    private lateinit var dialog : ProgressDialog
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog = indeterminateProgressDialog("Fething data...", "Please wait")
        dialog.setCancelable(false)
        dialog.dismiss()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun showProgressBar(state: Boolean) {
        if (state)
            dialog.show()
        else
            dialog.dismiss()
    }

    override fun showError(message: String?) {
        showSnackbar(
            message ?: "An error occurred.",
            Snackbar.LENGTH_LONG)
    }
}