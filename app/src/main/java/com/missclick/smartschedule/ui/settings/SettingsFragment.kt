package com.missclick.smartschedule.ui.settings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.view.get
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.adapters.SectionsPagerAdapter
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        super.onActivityCreated(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar_edit.visibility = View.GONE
        (activity as MainActivity).toolbar_save.visibility = View.GONE
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            viewModel.getSettings()
            withContext(Dispatchers.Main){
                settings_spinner_second_week.isChecked = viewModel.settings?.weeks ?: true == 2
                settings_spinner_count_of_max_lessons.setSelection((viewModel.settings?.couples ?: 3) - 3)
                settings_card_view_day.setOnClickListener {
                    val dialog = DaysDialogFragment(viewModel.daysStringToInt())
                    dialog.attachCallBack(object : DaysDialogFragment.DialogCallBack{
                        override fun setDays(days: List<Int>) {
                            viewModel.daysString = viewModel.daysIntToString(daysInt = days)
                            save()
                        }
                    })
                    dialog.show(childFragmentManager, "kek")
                }
                settings_spinner_count_of_max_lessons.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        save()
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        save()
                    }

                }
                settings_spinner_second_week.setOnCheckedChangeListener { buttonView, isChecked ->
                    save()
                }
            }
        }

    }

    fun save(){
        viewModel.save(
            lessons = settings_spinner_count_of_max_lessons.selectedItem.toString().toInt(),
            week = settings_spinner_second_week.isChecked
        )
    }

    override fun onPause() {
        super.onPause()
        //save()
    }

}