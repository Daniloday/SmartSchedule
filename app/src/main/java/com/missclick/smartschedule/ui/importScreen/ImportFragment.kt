package com.missclick.smartschedule.ui.importScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.import_fragment.*

class ImportFragment : Fragment() {

    private lateinit var importViewModel: ImportViewModel
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        importViewModel =
                ViewModelProviders.of(this).get(ImportViewModel::class.java)
        return inflater.inflate(R.layout.import_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).toolbar_edit.visibility = View.GONE
        (activity as MainActivity).toolbar_save.visibility = View.GONE
        if (id != null) edit_import.setText(id)
        button_import.setOnClickListener {
            importViewModel.import(edit_import.text.toString())
        }
    }

    companion object {
        fun newInstance(id : String):Bundle{
            return Bundle().apply {
                putString("id", id)
            }
        }
    }

}