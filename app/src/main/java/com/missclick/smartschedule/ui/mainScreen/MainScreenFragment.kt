package com.missclick.smartschedule.ui.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.missclick.smartschedule.App
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.SectionsPagerAdapter
import com.missclick.smartschedule.data.repository.LessonRepository
import com.missclick.smartschedule.di.AppComponent
import com.missclick.smartschedule.di.module.AppModule
import com.missclick.smartschedule.viewstates.MainViewStates
import kotlinx.android.synthetic.main.main_screen_fragment.*
import javax.inject.Inject


class MainScreenFragment : Fragment() {

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel
    @Inject lateinit var repository: LessonRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

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
        viewModel.kek()
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                is MainViewStates.LoadedState -> {
                    progress_bar.visibility = View.GONE
                    val viewPager: ViewPager = (activity as MainActivity).findViewById(R.id.view_pager)
                    viewPager.adapter = sectionsPagerAdapter
                    val tabs: TabLayout = (activity as MainActivity).findViewById(R.id.tab_dots)
                    tabs.setupWithViewPager(viewPager)
                }
                is MainViewStates.ErrorState -> {
                    //SRY
                }
                is MainViewStates.LoadingState -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is MainViewStates.NoDataState -> {
                    //PIZDUY sozdavat schedule
                }
            }
        })


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }



}