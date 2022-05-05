package com.orlik.crypt.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import com.orlik.crypt.ui.dialogs.ProfileDialog
import com.orlik.crypt.databinding.FragmentProfilesBinding

class ProfilesFragment : Fragment() {
    private lateinit var _binding: FragmentProfilesBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilesBinding.inflate(inflater, container, false)
        binding.fabNewProfile.setOnClickListener {
            openProfileDialog()
        }
        binding.btnChooseProfile.setOnClickListener {
            Toast.makeText(context, "HERE WE GO", Toast.LENGTH_LONG).show()
        }

        listenProfileDialog()

        return binding.root
    }

    private fun openProfileDialog(){
        ProfileDialog().show(parentFragmentManager, ProfileDialog.TAG)
    }

    private fun listenProfileDialog() {
        parentFragmentManager.setFragmentResultListener(
            ProfileDialog.REQUEST_KEY, this,
            FragmentResultListener {_, result ->
                val name = result.get("name")
                val desk = result.get("desc")
                val code = result.get("code")
                val hex = result.get("hex")
                Toast.makeText(requireContext(), "$name <-> $desk", Toast.LENGTH_LONG).show()
        })
    }
}