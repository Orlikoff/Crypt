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

class ProfileDialog(): DialogFragment() {
    private lateinit var _view: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        _view = View.inflate(requireContext(), R.layout.new_profile_dialog, null)
        val dialog = builder.setView(_view).create()

        _view.findViewById<Button>(R.id.btn_submit).setOnClickListener { if (sendInfo()) dismiss() }
        _view.findViewById<Button>(R.id.btn_deny_profile).setOnClickListener { dismiss() }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun sendInfo(): Boolean{
        val name = _view.findViewById<EditText>(R.id.te_name).text.toString()
        val desc = _view.findViewById<EditText>(R.id.te_desk).text.toString()
        val code = _view.findViewById<EditText>(R.id.te_code).text.toString()
        val hex = _view.findViewById<EditText>(R.id.te_hex).text.toString()

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