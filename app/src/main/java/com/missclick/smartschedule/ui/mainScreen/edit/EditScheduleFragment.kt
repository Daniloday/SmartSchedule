package com.missclick.smartschedule.ui.mainScreen.edit

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.missclick.smartschedule.R

class EditScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = EditScheduleFragment()
    }

    private lateinit var viewModel: EditScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_schedule_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditScheduleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}