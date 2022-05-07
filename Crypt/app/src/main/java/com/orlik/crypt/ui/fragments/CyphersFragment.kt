package com.orlik.crypt.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.btnSha.setOnClickListener { openCyphersDialog("SHA") }
        binding.btnCaesar.setOnClickListener { openCyphersDialog("CAESAR") }

        return binding.root
    }

    private fun openCyphersDialog(cypher: String){
        CypherDialog(cypher).show(parentFragmentManager, CypherDialog.TAG)
    }
}