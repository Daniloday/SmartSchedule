package com.missclick.smartschedule.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.missclick.smartschedule.ui.mainScreen.schedule.ScheduleFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return if(position == 0){
            ScheduleFragment.newInstance("from 1")
        } else
            ScheduleFragment.newInstance("from 2")
    }


    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}