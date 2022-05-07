package com.orlik.crypt.data.profile

import android.app.Application
import androidx.lifecycle.*
import com.orlik.crypt.data.profile.ProfileDatabase
import com.orlik.crypt.data.profile.ProfileEntity
import com.orlik.crypt.data.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfilesViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ProfileRepository

    init {
        val profileDao = ProfileDatabase.getDatabase(application).profileDao()
        repository = ProfileRepository(profileDao)
    }

    fun getProfiles(): List<ProfileEntity>{
        return repository.getAllProfiles()
    }

    fun addProfile(profile: ProfileEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProfile(profile)
        }
    }

    fun removeProfile(profile: ProfileEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeProfile(profile)
        }
    }
}