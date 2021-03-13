package com.polish.registernow.utils

import android.app.Dialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.polish.registernow.R

/**
 * handle navigation
 */
fun Fragment.navigate(id:Int){
    findNavController().navigate(id)
}

fun Fragment.showProgressBar(progresDialog:Dialog){
    // the dialog must have been initialized where it has been called for use
    // next inflate the layout.
    progresDialog.setContentView(R.layout.dialog_progress)
    // start the dialog display
    progresDialog.show()
}

fun Fragment.hideProgressBar(progressDialog:Dialog){
    progressDialog.dismiss()
}
