package com.orlik.cryptoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.orlik.cryptoapp.databinding.ActivityMainBinding
import com.orlik.cryptoapp.databinding.FragmentTestBinding

class TestFragment : Fragment() {
    private lateinit var binding: FragmentTestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navController = findNavController()
        binding = FragmentTestBinding.inflate(inflater, container, false)
        binding.btnCryptoapp.setOnClickListener{
            when (binding.teVariant.text.toString().toInt()){
                1 -> navController.navigate(R.id.action_testFragment_to_toGoFragment)
                2 -> navController.navigate(R.id.action_testFragment_to_oneMoreFragment)
                else -> navController.navigate(R.id.action_testFragment_to_toGoFragment)
            }
        }
        return binding.root
    }
}