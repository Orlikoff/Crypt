package com.orlik.crypt.ui.synchronizer

import com.orlik.crypt.data.profile.ProfileEntity

class Synchronizer {

    companion object {
        private var INSTANCE: Synchronizer? = null
        private var currentProfile: ProfileEntity? = null
        private var colorSetter: ((String) -> Unit)? = null
        private var profileRemover: ((ProfileEntity) -> Unit)? = null
        var darkTheme = false

        fun getInstance(): Synchronizer{
            if (INSTANCE == null){
                INSTANCE = Synchronizer()
            }
            return INSTANCE!!
        }

        fun setupColorSetter(newColorSetter: (String)->Unit){
            colorSetter = newColorSetter
        }

        fun setupProfileRemover(newProfileRemover: (ProfileEntity)->Unit){
            profileRemover = newProfileRemover
        }

        fun getCurrentProfile() = currentProfile

        fun removeProfile(profile: ProfileEntity) {
            profileRemover!!.invoke(profile)
            currentProfile = null
        }

        fun setCurrentProfile(newProfile: ProfileEntity?){
            currentProfile = newProfile
            colorSetter!!.invoke(currentProfile!!.hex)
        }

        fun loadProfileFromDb(){
            TODO("Sync with database")
        }
    }

}