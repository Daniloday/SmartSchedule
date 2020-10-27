package com.missclick.smartschedule.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.missclick.smartschedule.ui.mainScreen.schedule.ScheduleFragment

@Suppress("DEPRECATION")
class SectionsPagerAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if(position == 0){
            ScheduleFragment.newInstance(week = 1)
        } else{
            ScheduleFragment.newInstance(week = 2)
        }
    }

    override fun getCount(): Int {
        return 2
    }

}