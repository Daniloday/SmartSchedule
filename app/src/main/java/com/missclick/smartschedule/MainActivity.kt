package com.missclick.smartschedule

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.missclick.smartschedule.notifications.CustomMessage
import com.missclick.smartschedule.ui.importScreen.ImportFragment

class MainActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var id : String? = null
    val customMessage = CustomMessage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        cancelNotification()
        setNotification()
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_main_screen, R.id.nav_lessons, R.id.nav_import, R.id.nav_export, R.id.nav_settings, R.id.nav_contact ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val intent = intent
        val action = intent.action
        val data = intent.dataString
        val extras = intent?.extras
        extras?.let {
            if(it.containsKey("notif")){
                val zoom = it.getString("notif")
                val intentZoom = Intent(Intent.ACTION_VIEW, Uri.parse(zoom))
                startActivity(intentZoom)
            }
        }
        if (Intent.ACTION_VIEW == action && data != null) {
            id = data.substring(data.lastIndexOf("/") + 1)
            if(id != null) findNavController(R.id.nav_host_fragment).navigate(R.id.nav_import, ImportFragment.newInstance(id = id!!))
        }

    }


    fun setNotification(){
        customMessage.scheduleMsg(this)
    }

    fun cancelNotification(){
        customMessage.cancel(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}