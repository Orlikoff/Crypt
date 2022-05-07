package com.orlik.crypt.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orlik.crypt.data.profile.ProfileEntity
import com.orlik.crypt.data.profile.ProfilesViewModel
import com.orlik.crypt.ui.dialogs.ProfileDialog
import com.orlik.crypt.databinding.FragmentProfilesBinding
import com.orlik.crypt.ui.fragments.helpers.ProfileAdapter
import com.orlik.crypt.ui.synchronizer.Synchronizer

class ProfilesFragment : Fragment() {
    private lateinit var _binding: FragmentProfilesBinding
    private lateinit var layoutManager: LinearLayoutManager
    private val binding get() = _binding
    private lateinit var profileAdapter: ProfileAdapter

    private lateinit var mProfileViewModel: ProfilesViewModel

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

        // viewModel setup
        mProfileViewModel = ViewModelProvider(this).get(ProfilesViewModel::class.java)
        Synchronizer.setupProfileRemover {
            mProfileViewModel.removeProfile(it)
        }

        profileAdapter = ProfileAdapter(requireContext(), ArrayList(mProfileViewModel.getProfiles()))
        binding.rvProfiles.adapter = profileAdapter

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

        return binding.root
    }

    private fun openProfileDialog(){
        ProfileDialog().show(parentFragmentManager, ProfileDialog.TAG)
    }

    private fun listenProfileDialog() {
        parentFragmentManager.setFragmentResultListener(
            ProfileDialog.REQUEST_KEY, this
        ) { _, result ->
            val profile = ProfileEntity(
                0,
                result.get("name").toString(),
                result.get("desc").toString(),
                result.get("code").toString(),
                result.get("hex").toString()
            )

            mProfileViewModel.addProfile(profile)
            profileAdapter.addProfile(profile)

            Toast.makeText(requireContext(), "Profile has been added", Toast.LENGTH_SHORT)
                .show()
        }
    }
}