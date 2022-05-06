package com.orlik.crypt.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfilesViewModel: ViewModel() {
    private var _profileList: MutableLiveData<ArrayList<Profile>> = MutableLiveData(ArrayList(0))

    fun getData() = _profileList

    private fun loadData() {

    }
}