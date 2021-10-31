package com.example.thelynx

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.thelynx.databinding.ActivityMainBinding
import com.example.thelynx.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun setUpView(saveInstanceState: Bundle?) {
        setSupportActionBar(binding.appBarMain.toolbar)
        binding.appBarMain.apply {
            toolbar.setBackgroundResource(R.color.black)
            icFilter.setOnClickListener {
                Toast.makeText(it.context, "click", Toast.LENGTH_LONG).show()
            }
        }

        navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_github_users, R.id.nav_favourite, R.id.nav_setting
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}