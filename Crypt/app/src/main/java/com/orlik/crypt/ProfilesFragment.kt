package com.orlik.crypt

import android.app.Activity
import android.os.Bundle
import android.os.TokenWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.orlik.crypt.databinding.FragmentProfilesBinding

class ProfilesFragment : Fragment() {
    private lateinit var _binding: FragmentProfilesBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilesBinding.inflate(inflater, container, false)
        binding.fabNewProfile.setOnClickListener {
            val profileDialog = ProfileDialog(requireActivity(), context)
            profileDialog.startDialog()
        }
        binding.btnChooseProfile.setOnClickListener {
            Toast.makeText(context, "HERE WE GO", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }
}