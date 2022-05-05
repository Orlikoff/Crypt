package com.orlik.crypt.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.orlik.crypt.R
import com.orlik.crypt.databinding.CypherDialogBinding
import com.orlik.crypt.databinding.NewProfileDialogBinding

class ProfileDialog(): DialogFragment() {
    private lateinit var _binding: NewProfileDialogBinding
    private val binding get() = _binding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        _binding = NewProfileDialogBinding.inflate(layoutInflater, null, false)
        val dialog = builder.setView(binding.root).create()

        binding.btnSubmit.setOnClickListener { if (sendInfo()) dismiss() }
        binding.btnDenyProfile.setOnClickListener { dismiss() }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun sendInfo(): Boolean{
        val name = binding.teName.text.toString()
        val desc = binding.teDesk.text.toString()
        val code = binding.teCode.text.toString()
        val hex = binding.teHex.text.toString()

        for (item in arrayListOf<String>(name, desc, code, hex))
            if (item == ""){
                Toast.makeText(requireContext(), "Fill all the fields!", Toast.LENGTH_SHORT).show()
                return false
            }

        parentFragmentManager.setFragmentResult(
            REQUEST_KEY, bundleOf(
            "name" to name,
            "desc" to desc,
            "code" to code,
            "hex" to hex
        ))
        return true
    }

    companion object {
        const val TAG = "NewProfileDialog"
        const val REQUEST_KEY = "$TAG:defaultRequestKey"
    }
}