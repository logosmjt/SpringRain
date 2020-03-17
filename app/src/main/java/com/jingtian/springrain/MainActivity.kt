package com.jingtian.springrain

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.jingtian.springrain.helper.BottomNavigationViewHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val iconImageList = BottomNavigationViewHelper.getIconImageView(navView)
        for (i in iconImageList.indices) {
            Glide.with(baseContext)
                .load("https://upload.jianshu.io/users/upload_avatars/10599465/12257565-f94f-4fda-9dd3-a730722fb6e3?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240")
                .into(iconImageList[i])
        }
    }
}
