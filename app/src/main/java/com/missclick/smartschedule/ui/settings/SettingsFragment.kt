package com.missclick.smartschedule.ui.settings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (activity as MainActivity).toolbar_edit.visibility = View.GONE
        (activity as MainActivity).toolbar_save.visibility = View.GONE
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        settings_card_view_day.setOnClickListener {
            val dialog = DaysDialogFragment().show(childFragmentManager, "TestDialog")
        }

        // TODO: Use the ViewModel
    }

}