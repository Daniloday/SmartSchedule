package com.missclick.smartschedule

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

/*
TODO
DayEntity -> DayModel (ScheduleFragment ...)
LessonEntity -> LessonModel (AddLessonFragment)
 */

class MainActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    var isMainScreenFragment = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_create, R.id.nav_import, R.id.nav_export, R.id.nav_settings, R.id.nav_main_screen, R.id.nav_lessons), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        navView.setNavigationItemSelectedListener(this)
        navController.addOnDestinationChangedListener {navi, destination, _ ->
            if(destination.id == R.id.nav_main_screen) {
                Log.e("mainAct","if")

            } else {
                Log.e("mainAct","else")
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        Log.e("mainAct",id.toString())
//        if (id == R.id.nav_main_screen) {
//            if (!isMainScreenFragment)
//                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_main_screen)
//        }
//
//        return true
//    }

}