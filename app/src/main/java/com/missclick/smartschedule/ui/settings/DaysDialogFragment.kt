package com.missclick.smartschedule.ui.settings

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.missclick.smartschedule.R
import kotlinx.coroutines.NonCancellable.cancel


class DaysDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val selectedItems = ArrayList<Int>()
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
                    // FIRE ZE MISSILES!
                })
            .setNegativeButton("Otmena",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                })
        return builder.create()
    }



//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        // Use the Builder class for convenient dialog construction
//        val builder =
//            AlertDialog.Builder(activity)
//        builder.setMessage("hihi")
//            .setPositiveButton("Okes",
//                DialogInterface.OnClickListener { dialog, id ->
//                    // FIRE ZE MISSILES!
//                })
//            .setNegativeButton(R.string.cancel,
//                DialogInterface.OnClickListener { dialog, id ->
//                    // User cancelled the dialog
//                })
//        // Create the AlertDialog object and return it
//        return builder.create()
//    }
}