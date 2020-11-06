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
import java.util.*
import javax.inject.Inject

class MainScreenFragment : Fragment() {

    private lateinit var viewModel: MainScreenViewModel
    @Inject lateinit var repository: LessonRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainScreenViewModel::class.java)
        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                childFragmentManager
            )

//        viewModel.kek()
//        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
//            when(state){
//                is MainViewStates.LoadedState -> {
//                    progress_bar.visibility = View.GONE
//                    val viewPager: ViewPager = (activity as MainActivity).findViewById(R.id.view_pager)
//                    viewPager.adapter = sectionsPagerAdapter
//                    val tabs: TabLayout = (activity as MainActivity).findViewById(R.id.tab_dots)
//                    tabs.setupWithViewPager(viewPager)
//                }
//                is MainViewStates.ErrorState -> {
//                    //SRY
//                }
//                is MainViewStates.LoadingState -> {
//                    progress_bar.visibility = View.VISIBLE
//                }
//                is MainViewStates.NoDataState -> {
//                    //PIZDUY sozdavat schedule
//                }
//            }
//        })

//        val viewPager: ViewPager = (activity as MainActivity).findViewById(R.id.view_pager)
        view_pager.adapter = sectionsPagerAdapter

        // for using first or second week by default
        val c = Calendar.getInstance()
        view_pager.currentItem = (c.get(Calendar.WEEK_OF_YEAR) / 2) + 1

        val tabs: TabLayout = (activity as MainActivity).findViewById(R.id.tab_dots)
        tabs.setupWithViewPager(view_pager)

    }

}