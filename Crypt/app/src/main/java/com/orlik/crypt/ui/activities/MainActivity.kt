package com.orlik.crypt.ui.activities

import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.WindowManager
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.orlik.crypt.R
import com.orlik.crypt.databinding.ActivityMainBinding
import com.orlik.crypt.ui.synchronizer.Synchronizer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Change status bar color
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK==
                Configuration.UI_MODE_NIGHT_YES){
                window.statusBarColor = resources.getColor(R.color.middletonDARK)
                Synchronizer.darkTheme = true
            }
            else {
                window.statusBarColor = resources.getColor(R.color.middletonLIGHT)
            }
        }

        // Navigation setup
        val bottomNavigationView = binding.bottomNavView
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)

        // Synchronizer setup
        Synchronizer.setupColorSetter {
            binding.ivCurrentProfile.setColorFilter(Color.parseColor(it))
        }
    }
}