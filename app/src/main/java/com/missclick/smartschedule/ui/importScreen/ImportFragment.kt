package com.missclick.smartschedule.ui.importScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.missclick.smartschedule.R
import kotlinx.android.synthetic.main.import_fragment.*

class ImportFragment : Fragment() {

    private lateinit var importViewModel: ImportViewModel

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
        button_import.setOnClickListener {
            importViewModel.import(edit_import.text.toString())
        }
    }


}