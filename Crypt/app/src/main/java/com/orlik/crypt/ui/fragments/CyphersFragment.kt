package com.orlik.crypt.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import com.orlik.crypt.ui.dialogs.CypherDialog
import com.orlik.crypt.databinding.FragmentCyphersBinding

class CyphersFragment : Fragment() {
    private lateinit var _binding: FragmentCyphersBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCyphersBinding.inflate(inflater, container, false)

        binding.btnSha.setOnClickListener { openCyphersDialog() }

        listenCyphersDialog()

        return binding.root
    }

    private fun openCyphersDialog(){
        CypherDialog().show(parentFragmentManager, CypherDialog.TAG)
    }

    private fun listenCyphersDialog() {
        parentFragmentManager.setFragmentResultListener(
            CypherDialog.REQUEST_KEY, this,
            FragmentResultListener {_, result ->
                Toast.makeText(requireContext(), "Here we go again", Toast.LENGTH_SHORT).show()
            }
        )
    }
}