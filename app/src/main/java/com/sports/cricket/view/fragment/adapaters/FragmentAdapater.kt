package com.sports.cricket.view.fragment.adapaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapater(
    fragmentActivity: FragmentActivity,
    val arraylistofFragment: ArrayList<Fragment>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = arraylistofFragment.size

    override fun createFragment(position: Int) = arraylistofFragment.get(position)
}