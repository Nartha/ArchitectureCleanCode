package com.example.thelynx.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {
    private var viewBinding: VB? = null

    val binding get() = viewBinding!!

    abstract fun getViewBinding(): VB
    abstract fun setView(): Dialog
    abstract fun bind()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewBinding = getViewBinding()
        bind()
        return setView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}