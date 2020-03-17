package com.jingtian.springrain.helper

import android.widget.ImageView
import androidx.core.view.children
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jingtian.springrain.R

object BottomNavigationViewHelper {
    fun getIconImageView(navView: BottomNavigationView):List<ImageView> {
        val menuView = navView.getChildAt(0) as BottomNavigationMenuView
        return menuView.children.map {
            return@map it.findViewById<ImageView>(R.id.icon)
        }.toList()
    }
}