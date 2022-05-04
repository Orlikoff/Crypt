package com.orlik.crypt

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.orlik.crypt.databinding.NewProfileDialogBinding

class ProfileDialog(parent: Activity, private val context: Context?) {
    private lateinit var view: View
    private lateinit var dialog: AlertDialog
    private val activity: Activity = parent

    fun startDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        view = inflater.inflate(R.layout.new_profile_dialog, null)

        builder.setView(view)
        builder.setCancelable(false)

        dialog = builder.create()
        view.findViewById<Button>(R.id.btn_submit).setOnClickListener {
            fetchInfo()
            dismissDialog()
        }
        view.findViewById<Button>(R.id.btn_deny_profile).setOnClickListener {
            dismissDialog()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    // TODO: rewrite using DialogFragment

    private fun fetchInfo() {
        val name = view.findViewById<EditText>(R.id.te_name).text.toString()
        val desk = view.findViewById<EditText>(R.id.te_desk).text.toString()
        val code = view.findViewById<EditText>(R.id.te_code).text.toString()
        val hex = view.findViewById<EditText>(R.id.te_hex).text.toString()
        Toast.makeText(context, "$name --- $desk --- $code --- $hex", Toast.LENGTH_LONG)
    }

    private fun dismissDialog() {
        dialog.dismiss()
    }
}