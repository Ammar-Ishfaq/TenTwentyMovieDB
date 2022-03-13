package com.muhammad_ammar.tentwenty.view.activities

import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.muhammad_ammar.tentwenty.R
import com.muhammad_ammar.tentwenty.view.activities.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var navController: NavController
    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment?
        val inflater = navHostFragment?.navController?.navInflater
        val graph = inflater?.inflate(R.navigation.mobile_navigation)

        graph?.startDestination = R.id.navigation_home

        if (graph != null) {
            navHostFragment?.navController?.graph = graph
        }



        navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

//        setupActionBarWithNavController(
//            navController,
//            appBarConfiguration
//        )//this will hide the title bar titles
        nav_view.setupWithNavController(navController)
    }
}