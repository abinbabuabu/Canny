package com.emilda.canny.Activity

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.emilda.canny.Fragments.OrderBottomSheet
import com.emilda.canny.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotlinx.android.synthetic.main.fragment_main_screen.*

class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var NavController:NavController
    lateinit var  appbarConfig:AppBarConfiguration


    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val toolbar: Toolbar = findViewById(R.id.toolbar)



        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        NavController = findNavController(R.id.main_nav_host_fragment)
        appbarConfig= AppBarConfiguration(setOf(R.id.main_screen),drawerLayout)
        toolbar.setupWithNavController(NavController,appbarConfig)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        toggle.isDrawerIndicatorEnabled = false
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
       // centerActionBarTitle()

        NavigationUI.setupWithNavController(navView,NavController)
        navView.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.menu_profile ->{}
            R.id.menu_address ->{}
            R.id.menu_customer_support ->{}
            R.id.menu_feedback ->{
               val navController = Navigation.findNavController(this,R.id.main_nav_host_fragment)
                navController.navigate(R.id.feedbackFragment)
            }
            R.id.menu_rate_us ->{
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(
                        "https://play.google.com/store/apps/details?id=com.example.android")
                    setPackage("com.android.vending")
                }
                startActivity(intent)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun centerActionBarTitle() {

        var titleId = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            titleId = getResources().getIdentifier("action_bar_title", "id", "android")
        } else {
            // This is the id is from your app's generated R class when ActionBarActivity is used
            // for SupportActionBar
            titleId = R.id.action_bar_title
        }

        // Final check for non-zero invalid id
        if (titleId > 0) {
            val titleTextView = findViewById<TextView>(titleId)

            val metrics = resources.displayMetrics

            // Fetch layout parameters of titleTextView (LinearLayout.LayoutParams : Info from HierarchyViewer)
            val titleTxvPars = titleTextView.layoutParams as ActionBar.LayoutParams
            titleTxvPars.gravity = Gravity.CENTER_HORIZONTAL
            titleTxvPars.width = metrics.widthPixels

            titleTextView.setLayoutParams(titleTxvPars)
            titleTextView.setGravity(Gravity.CENTER)
        }
    }

}
