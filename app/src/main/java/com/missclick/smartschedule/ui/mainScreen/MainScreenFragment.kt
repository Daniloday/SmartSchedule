package com.missclick.smartschedule.ui.mainScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.SectionsPagerAdapter
import com.missclick.smartschedule.data.repository.LessonRepository
import kotlinx.android.synthetic.main.main_screen_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class MainScreenFragment : Fragment() {

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainScreenViewModel::class.java)
        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.getSettings()
            withContext(Dispatchers.Main){
                val sectionsPagerAdapter =
                    SectionsPagerAdapter(
                        childFragmentManager,
                        viewModel.settings!!.weeks
                    )
                view_pager.adapter = sectionsPagerAdapter

                // for using first or second week by default
                val c = Calendar.getInstance()
                view_pager.currentItem = (c.get(Calendar.WEEK_OF_YEAR) % 2)

                if (viewModel.settings!!.weeks == 2){
                    val tabs: TabLayout = (activity as MainActivity).findViewById(R.id.tab_dots)
                    tabs.setupWithViewPager(view_pager)
                }
            }
        }

    }

}