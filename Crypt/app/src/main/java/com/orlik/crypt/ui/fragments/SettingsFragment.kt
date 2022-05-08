package com.orlik.crypt.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.orlik.crypt.databinding.FragmentSettingsBinding
import com.orlik.crypt.ui.synchronizer.Synchronizer

class SettingsFragment : Fragment() {
    private lateinit var _binding: FragmentSettingsBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        binding.swTheme.setOnCheckedChangeListener { _, state ->
            if (state){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.btnAppInfo.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Developer")
                .setMessage("Developed by Ivan Orlovskiy\nGitHub: Orlikoff")
                .setPositiveButton("COOL"
                ) { _, _ ->
                    Toast.makeText(requireContext(), "Good luck!", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        if (Synchronizer.darkTheme){
            binding.swTheme.isChecked = true
        }

        return binding.root
    }

}