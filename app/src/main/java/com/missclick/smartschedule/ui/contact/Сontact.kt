package com.missclick.smartschedule.ui.contact

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.missclick.smartschedule.R
import kotlinx.android.synthetic.main.contact_fragment.*
import kotlinx.android.synthetic.main.lesson_info_fragment_reborn.*

class Contact : Fragment() {

    companion object {
        fun newInstance() = Contact()
    }

    private lateinit var viewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        return inflater.inflate(R.layout.contact_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_telegram_contact.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://t.me/missclickcorporation"))
            startActivity(intent)
        }
        button_email_contact.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:missclick.corporation@gmail.com"))
            startActivity(intent)
        }


    }

}