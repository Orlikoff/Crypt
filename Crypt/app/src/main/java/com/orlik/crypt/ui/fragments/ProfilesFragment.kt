package com.orlik.crypt.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orlik.crypt.data.Profile
import com.orlik.crypt.data.ProfilesViewModel
import com.orlik.crypt.ui.dialogs.ProfileDialog
import com.orlik.crypt.databinding.FragmentProfilesBinding
import com.orlik.crypt.ui.fragments.helpers.ProfileAdapter

class ProfilesFragment : Fragment() {
    private lateinit var _binding: FragmentProfilesBinding
    private lateinit var layoutManager: LinearLayoutManager
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // binding setup
        _binding = FragmentProfilesBinding.inflate(inflater, container, false)

        // dialog setup
        binding.fabNewProfile.setOnClickListener {
            openProfileDialog()
        }

        // RecyclerView setup
        layoutManager = LinearLayoutManager(requireContext())
        binding.rvProfiles.layoutManager = layoutManager
        // TODO: Change to database synchronization
        val profileAdapter = ProfileAdapter(requireContext(), (1..15).map {
            Profile(
                id = it,
                name = "Name of $it",
                desc = "Description of $it",
                code = "Code of $it",
                hex = "#AABBCC"
            )
        })
        binding.rvProfiles.adapter = profileAdapter
        // fab hide/show animation
        binding.rvProfiles.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 30 && binding.fabNewProfile.isShown) {
                    binding.fabNewProfile.hide()
                }
                if (dy < -10 && !binding.fabNewProfile.isShown) {
                    binding.fabNewProfile.show()
                }
            }
        })

        listenProfileDialog()

        // viewModel setup
        val profileModel = ViewModelProvider(requireActivity()).get(ProfilesViewModel::class.java)
        val data = profileModel.getData()
        data.observe(viewLifecycleOwner, Observer {
            // TODO: Here update the views (RecyclerView)
            Toast.makeText(
                requireContext(),
                "Data has been updated",
                Toast.LENGTH_SHORT
            ).show()
        })

        return binding.root
    }

    private fun openProfileDialog(){
        ProfileDialog().show(parentFragmentManager, ProfileDialog.TAG)
    }

    private fun listenProfileDialog() {
        parentFragmentManager.setFragmentResultListener(
            ProfileDialog.REQUEST_KEY, this
        ) { _, result ->
            val name = result.get("name")
            val desk = result.get("desc")
            // TODO: Implement database here
//            val code = result.get("code")
//            val hex = result.get("hex")
            Toast.makeText(requireContext(), "$name <-> $desk", Toast.LENGTH_LONG).show()
        }
    }
}