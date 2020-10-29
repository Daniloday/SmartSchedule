package com.missclick.smartschedule.ui.export

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.missclick.smartschedule.R
import kotlinx.android.synthetic.main.export_fragment.*

class ExportFragment : Fragment() {

    private lateinit var exportViewModel: ExportViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        exportViewModel =
                ViewModelProviders.of(this).get(ExportViewModel::class.java)
        val root = inflater.inflate(R.layout.export_fragment, container, false)

        exportViewModel.text.observe(viewLifecycleOwner, Observer {
           //we can somethings write here
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_export.setOnClickListener {
            exportViewModel.export()
        }
    }
}