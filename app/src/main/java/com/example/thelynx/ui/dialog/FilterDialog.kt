package com.example.thelynx.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.example.thelynx.R
import com.example.thelynx.databinding.DialogFilterBinding

class FilterDialog: BaseDialogFragment<DialogFilterBinding> (){

    val clickFilterASC = MutableLiveData<String>()
    val clickFilterDESC = MutableLiveData<String>()

    override fun getViewBinding() = DialogFilterBinding.inflate(layoutInflater)

    override fun setView(): Dialog {
        val dialog = Dialog(requireContext())
        dialog.window?.apply {
            setContentView(binding.root)
            attributes.width = WindowManager.LayoutParams.WRAP_CONTENT
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
            attributes.gravity = Gravity.END or Gravity.TOP
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return dialog
    }

    override fun bind() {
        binding.btnFilterASC.setOnClickListener {
            clickFilterASC.postValue("FilterASC")
            dismiss()
        }

        binding.btnFilterDESC.setOnClickListener {
            clickFilterDESC.postValue("FilterDESC")
            dismiss()
        }
    }

}