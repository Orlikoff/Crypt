package com.orlik.crypt.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.orlik.crypt.databinding.CypherDialogBinding

class CypherDialog: DialogFragment() {
    private lateinit var _binding: CypherDialogBinding
    private val binding get() = _binding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        _binding = CypherDialogBinding.inflate(layoutInflater, null, false)
        val dialog = builder.setView(binding.root).create()

        binding.btnStartSync.setOnClickListener {
            Toast.makeText(requireContext(), "Hello", Toast.LENGTH_SHORT).show()
            sendInfo()
            dismiss()
        }
        binding.btnDenyCypher.setOnClickListener { dismiss() }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun sendInfo() =
        parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf("ass" to 123))

    companion object {
        const val TAG = "CyphersDialog"
        const val REQUEST_KEY = "$TAG:defaultRequestKey"
    }
}