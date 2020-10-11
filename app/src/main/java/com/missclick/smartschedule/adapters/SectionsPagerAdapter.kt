package com.missclick.smartschedule.adapters

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.missclick.smartschedule.ui.gallery.GalleryFragment
import com.missclick.smartschedule.ui.mainScreen.schedule.ScheduleFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return if(position == 0){
            Log.e("adapter", "schedule")
            ScheduleFragment.newInstance("from 1")
        } else{
            Log.e("adapter", "schedule")
            ScheduleFragment.newInstance("from 2")
        }
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}