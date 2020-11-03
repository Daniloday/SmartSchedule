package com.missclick.smartschedule.ui.export

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.missclick.smartschedule.MainActivity
import com.missclick.smartschedule.R
import com.missclick.smartschedule.viewstates.ExportViewStates
import kotlinx.android.synthetic.main.app_bar_main.*
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
        (activity as MainActivity).toolbar_edit.visibility = View.GONE
        (activity as MainActivity).toolbar_save.visibility = View.GONE
        exportViewModel.stateData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is ExportViewStates.LoadingState -> {
                    progress_bar_export.visibility = View.VISIBLE
                    exportViewModel.export()
                }
                is ExportViewStates.LoadedState -> {
                    progress_bar_export.visibility = View.GONE
                    text_your_code_help.visibility = View.VISIBLE
                    text_your_code.visibility = View.VISIBLE
                    button_share.visibility = View.VISIBLE
                    text_your_code.text = state.id
                }
                is ExportViewStates.ErrorState -> {
                    progress_bar_export.visibility = View.GONE
                    text_your_code.visibility = View.VISIBLE
                    text_your_code.text = state.e
                }
            }
        })

        text_your_code.setOnClickListener {
            val clipboard: ClipboardManager? = //getSystemService<Any>(Context.CLIPBOARD_SERVICE) as ClipboardManager?
                (context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
            val clip = ClipData.newPlainText(label.toString(), text_your_code.text)
            clipboard!!.setPrimaryClip(clip)
            Toast.makeText(context,"Copied", Toast.LENGTH_SHORT).show()
        }

        button_share.setOnClickListener {
            val text = "Hello!\nWelcome to my schedule\nIt is id:\n${text_your_code.text}"
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

    }
}