package com.missclick.smartschedule.ui.settings

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.missclick.smartschedule.R
import kotlinx.coroutines.NonCancellable.cancel


class DaysDialogFragment(private val defaultDays : MutableList<Int>) : DialogFragment() {

    var callback  : DialogCallBack? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val selectedItems = defaultDays
        val allDays = listOf("Monday","Tuesday","Wednesday","Thursday","Friday", "Saturday", "Sunday")
        Log.e("Kek", allDays.toString())
        val checkedItem = mutableListOf<Boolean>()
        for(i in allDays.indices)
            checkedItem.add(i, false)
        for(item in selectedItems)
            checkedItem[item] = true
        val builder =
            AlertDialog.Builder(activity)
        builder.setTitle("Choose days with lessons")
            .setMultiChoiceItems(R.array.week_days, checkedItem.toBooleanArray()
            ) { dialog, which, isChecked ->
                if(isChecked)
                    selectedItems.add(which)
                else if(selectedItems.contains(which))
                    selectedItems.remove(which)
            }
            .setPositiveButton("Save",
                DialogInterface.OnClickListener { dialog, id ->
                    callback?.setDays(selectedItems)
                })
            .setNegativeButton("Cancel",
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
