package com.orlik.crypt.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.orlik.crypt.data.api.Requester
import com.orlik.crypt.databinding.CypherDialogBinding
import com.orlik.crypt.ui.synchronizer.Synchronizer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CypherDialog(private val cypher: String): DialogFragment() {
    private lateinit var _binding: CypherDialogBinding
    private val binding get() = _binding
    private var result = MutableLiveData<String>("")
    private var tempResult: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        _binding = CypherDialogBinding.inflate(layoutInflater, null, false)
        val dialog = builder.setView(binding.root).create()

        binding.tvTitleCypher.text = cypher

        val outputObserver = Observer<String>{
            binding.teOutput.setText(it)
        }

        result.observe(this, outputObserver)

        binding.btnStartSync.setOnClickListener {
            when {
                Synchronizer.getCurrentProfile() == null -> {
                    Toast.makeText(requireContext(), "Choose the profile!", Toast.LENGTH_SHORT).show()
                }
                binding.teInput.text.toString().isEmpty() -> {
                    Toast.makeText(requireContext(), "Fill the input!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Wait...", Toast.LENGTH_SHORT).show()
                    lifecycleScope.launch(Dispatchers.IO) {
                        tempResult = sendInfo()
                    }.invokeOnCompletion {
                        if (tempResult != null){
                            result.postValue(tempResult!!)
                        } else {
                            Toast.makeText(requireContext(), "Empty result!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        binding.btnDenyCypher.setOnClickListener { dismiss() }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun sendInfo(): String {
        return Requester.performRequest(
            cypher,
            binding.teInput.text.toString(),
            binding.swCypherMode.isChecked
        )
    }

    companion object {
        const val TAG = "CyphersDialog"
        const val REQUEST_KEY = "$TAG:defaultRequestKey"
    }
}