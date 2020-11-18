package com.missclick.smartschedule.ui.settings

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.missclick.smartschedule.R
import kotlinx.coroutines.NonCancellable.cancel


class DaysDialogFragment(private val defaultDays : MutableList<Int>) : DialogFragment() {

    var callback  : DialogCallBack? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val selectedItems = defaultDays
        val builder =
            AlertDialog.Builder(activity)
        builder.setTitle("Choose days with lessons")
            .setMultiChoiceItems(R.array.week_days, null, object : DialogInterface.OnMultiChoiceClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                    if(isChecked)
                        selectedItems.add(which)
                    else if(selectedItems.contains(which))
                        selectedItems.remove(which)
                }
            })
            .setPositiveButton("Okes",
                DialogInterface.OnClickListener { dialog, id ->
                    callback?.setDays(selectedItems)
                })
            .setNegativeButton("Otmena",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                })
        return builder.create()
    }

    fun attachCallBack(callback : DialogCallBack){
        this.callback = callback
    }

    interface DialogCallBack{
        fun setDays(days : List<Int>)
    }

}
