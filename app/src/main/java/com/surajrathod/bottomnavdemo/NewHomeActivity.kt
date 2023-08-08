package com.surajrathod.bottomnavdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.surajrathod.bottomnavdemo.databinding.ActivityNewHomeBinding

class NewHomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewHomeBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.fab.setOnClickListener {
            //navController.popBackStack(R.id.homeFragment,true)
            navController.navigate(R.id.homeFragment)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navController.navigateUp()
    }
    private fun setupViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        navController = navHostFragment.navController
        binding.newBottomBar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                val backStackEntry = navController.currentBackStack.value.map {
                    resources.getResourceEntryName(it.destination.id)
                }
                var myString = ""
                backStackEntry.forEach {
                    myString += ",$it"
                }
                binding.txtBackStack.text = myString
            }

        })
    }
}