package com.mneybox.calculator.x

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.mneybox.calculator.x.databinding.ColorDialogBinding

class ColorDialog(val callback: (Int)->Unit): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var adb = AlertDialog.Builder(requireContext())
        val binding = ColorDialogBinding.inflate(layoutInflater)
        val colors = arrayListOf(R.color.red,R.color.or,R.color.fiol,R.color.green,R.color.pink,R.color.gray1,R.color.green1,R.color.grey2,R.color.blue)
        val views = arrayListOf(binding.c1,binding.c2,binding.c3,binding.c4,binding.c5,binding.c6,binding.c7,binding.c8,binding.c9,)
        for(i in views.indices) {
            views[i].setOnClickListener {
                callback(colors[i])
                dismiss()
            }
        }
        adb = adb.setView(binding.root)
        val dialog = adb.create()
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

}