package com.orlik.crypt.ui.synchronizer

import android.graphics.Color
import android.graphics.ColorFilter
import android.widget.ImageView
import com.orlik.crypt.data.Profile

class Synchronizer(private val imageView: ImageView) {
    private var currentProfile: Profile? = null

    fun getCurrentProfile() = currentProfile

    fun setCurrentProfile(newProfile: Profile?){
        currentProfile = newProfile
        imageView.setColorFilter(Color.parseColor(currentProfile?.hex))
    }
}