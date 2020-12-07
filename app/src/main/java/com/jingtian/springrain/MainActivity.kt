package com.jingtian.springrain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jingtian.springrain.helper.BottomNavigationViewHelper
import com.jingtian.springrain.ui.dashboard.DashboardFragment
import com.jingtian.springrain.ui.home.HomeFragment
import com.jingtian.springrain.ui.notifications.NotificationsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.GreenTheme)
        setContentView(R.layout.activity_main)

        supportActionBar
            ?.hide()

        val viewPager2: ViewPager2 = findViewById(R.id.viewPager2)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomeFragment()
                    1 -> DashboardFragment()
                    else -> NotificationsFragment()
                }
            }
        }

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home ->
                    viewPager2.setCurrentItem(0, true)
                R.id.navigation_dashboard ->
                    viewPager2.setCurrentItem(1, true)

                R.id.navigation_notifications ->
                    viewPager2.setCurrentItem(2, true)
            }
            true
        }

        val iconImageList = BottomNavigationViewHelper.getIconImageView(navView)
        for (i in iconImageList.indices) {
            Glide.with(baseContext)
                .load("https://upload.jianshu.io/users/upload_avatars/10599465/12257565-f94f-4fda-9dd3-a730722fb6e3?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240")
                .into(iconImageList[i])
        }
    }
}
