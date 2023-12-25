package com.mneybox.calculator.x

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.mneybox.calculator.x.databinding.AddDialogBinding

class AddDialog(val add: Boolean, val callback: (Int)->Unit): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var adb = AlertDialog.Builder(requireContext())
        val binding = AddDialogBinding.inflate(layoutInflater)
        binding.add.visibility = if(add) View.VISIBLE else View.INVISIBLE
        binding.minus.visibility = if(!add) View.VISIBLE else View.INVISIBLE
        binding.add.setOnClickListener {
            callback(binding.number.text.toString().toInt())
            dismiss()
        }
        binding.minus.setOnClickListener {
            callback(-1*binding.number.text.toString().toInt())
            dismiss()
        }
        binding.number.setTextColor(requireContext().getColor(if(add) R.color.green else R.color.red))
        adb = adb.setView(binding.root)
        val dialog = adb.create()
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }
}