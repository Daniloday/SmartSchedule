package com.missclick.smartschedule.ui.export

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.missclick.smartschedule.MainActivity
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

        exportViewModel.idCode.observe(viewLifecycleOwner, Observer {
            text_your_code.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_export.setOnClickListener {
            exportViewModel.export()
            text_your_code_help.visibility = View.VISIBLE
            text_your_code.visibility = View.VISIBLE
            button_copy.visibility = View.VISIBLE
        }

        button_copy.setOnClickListener {
            val clipboard: ClipboardManager? = //getSystemService<Any>(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            (context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
            val clip = ClipData.newPlainText(label.toString(),  text_your_code.text)
            clipboard!!.setPrimaryClip(clip)
        }
    }
}