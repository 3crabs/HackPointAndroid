package com.threecrabs.hackpoint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.threecrabs.hackpoint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private val viewModel: BaseViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        changeAuth()
    }

    fun changeAuth() {
        if (viewModel.isAuth) {
            navController.setGraph(R.navigation.mobile_navigation_auth)
        } else {
            navController.setGraph(R.navigation.mobile_navigation_not_auth)
        }
    }
}