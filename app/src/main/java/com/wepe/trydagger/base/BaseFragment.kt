package com.wepe.trydagger.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.wepe.trydagger.extention.showSnackbar
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.toast

abstract class BaseFragment : Fragment(), BaseView{
    private lateinit var dialog : ProgressDialog
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog = indeterminateProgressDialog("Fething data...", "Please wait")
        dialog.setCancelable(false)
        dialog.dismiss()
    }

    override fun showProgressBar(state: Boolean) {
        if (state)
            dialog.show()
        else
            dialog.dismiss()
    }

    override fun showError(message: String?) {
        showSnackbar(
            message ?: "Terjadi kesalahan pada sistem",
            Snackbar.LENGTH_LONG)
    }
}