package com.missclick.smartschedule.ui.mainScreen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.SectionsPagerAdapter

class MainScreenFragment : Fragment() {

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                activity as MainActivity,
                childFragmentManager
            )
        val viewPager: ViewPager = (activity as MainActivity).findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = (activity as MainActivity).findViewById(R.id.tabs)
//        tabs.setupWithViewPager(viewPager)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}