package com.orlik.crypt.data

import androidx.lifecycle.LiveData

class ProfileRepository(private val profileDao: ProfileDao) {

    val readAllData: LiveData<List<ProfileEntity>> = profileDao.getAll()

}