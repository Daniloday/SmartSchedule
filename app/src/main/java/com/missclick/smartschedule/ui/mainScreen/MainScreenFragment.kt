package com.missclick.smartschedule.ui.mainScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.SectionsPagerAdapter
import com.missclick.smartschedule.data.repository.LessonRepository
import com.missclick.smartschedule.viewstates.MainViewStates
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.main_screen_fragment.*
import java.lang.reflect.Array.get
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
        Log.e("main", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                activity as MainActivity,
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
        val tabs: TabLayout = (activity as MainActivity).findViewById(R.id.tab_dots)
        tabs.setupWithViewPager(view_pager)

    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).toolbar_edit.visibility = View.GONE
        (activity as MainActivity).toolbar_save.visibility = View.GONE
        (activity as MainActivity).isMainScreenFragment = false
    }

}