package com.missclick.smartschedule.ui.mainScreen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.SectionsPagerAdapter
import com.missclick.smartschedule.viewstates.MainViewStates

class MainScreenFragment : Fragment() {

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)
        val root = inflater.inflate(R.layout.main_screen_fragment, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                activity as MainActivity,
                childFragmentManager
            )
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                is MainViewStates.LoadedState -> {
                    //LOADED
                    val viewPager: ViewPager = (activity as MainActivity).findViewById(R.id.view_pager)
                    viewPager.adapter = sectionsPagerAdapter
                }
                is MainViewStates.ErrorState -> {
                    //SRY
                }
                is MainViewStates.LoadingState -> {
                    //DOBAVIT PROGRESS BAR
                }
                is MainViewStates.NoDataState -> {
                    //PIZDUY sozdavat schedule
                }
            }
        })
//        val viewPager: ViewPager = (activity as MainActivity).findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = (activity as MainActivity).findViewById(R.id.tabs)
//        tabs.setupWithViewPager(viewPager)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}