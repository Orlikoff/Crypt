package com.orlik.crypt.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.orlik.crypt.R

class CypherDialog: DialogFragment() {
    private lateinit var _view: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        _view = View.inflate(requireContext(), R.layout.cypher_dialog, null)
        val dialog = builder.setView(_view).create()

        _view.findViewById<Button>(R.id.btn_start_sync).setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Hello",
                Toast.LENGTH_SHORT
            ).show()
            sendInfo()
            dismiss()
        }
        _view.findViewById<Button>(R.id.btn_deny_cypher).setOnClickListener { dismiss() }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun sendInfo() {
        parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf("ass" to 123))
    }

    companion object {
        const val TAG = "CyphersDialog"
        const val REQUEST_KEY = "$TAG:defaultRequestKey"
    }
}