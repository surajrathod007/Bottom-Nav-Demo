package com.surajrathod.bottomnavdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.surajrathod.bottomnavdemo.databinding.ActivityHome2Binding
import com.surajrathod.bottomnavdemo.databinding.ActivityHomeBinding

class HomeActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityHome2Binding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHome2Binding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.llBottom.forEach {
                if (it is Button) {
                    val buttonId = resources.getResourceEntryName(it.id)
                    it.isEnabled = !matchDestination(it.id, navController)
                }
            }
        }
        setContentView(binding.root)
        setupBottomBar()
    }

    private fun setupBottomBar() {
        binding.llBottom.forEach {
            if (it is Button) {
                it.setOnClickListener {
                    onNavigationDestinationSelected(it as Button)
                }
            }
        }
    }

    fun onNavigationDestinationSelected(
        button: Button,
    ): Boolean {
        val builder = NavOptions.Builder().setLaunchSingleTop(true).setRestoreState(true)
        val buttonId = resources.getResourceEntryName(button.id)
            if (buttonId == "btnHome") {
                builder.setPopUpTo(
                    navController.graph.findStartDestination().id,
                    inclusive = true,
                    saveState = false
                )
            }
        val backStackEntry = navController.currentBackStack.value.map {
            resources.getResourceEntryName(it.destination.id)
        }
        if(backStackEntry.any { it == buttonId }){

        }
        Toast.makeText(this@HomeActivity2,"$backStackEntry",Toast.LENGTH_LONG).show()
        val options = builder.build()
        return try {
            // TODO provide proper API instead of using Exceptions as Control-Flow.
            navController.navigate(getButtonIdFromString(buttonId), null, options)
            // Return true only if the destination we've navigated to matches the MenuItem
            matchDestination(getButtonIdFromString(buttonId), navController) == true
        } catch (e: IllegalArgumentException) {
            val name = NavDestination.getDisplayName(
                navController.context,
                getButtonIdFromString(buttonId)
            )
            Log.i(
                "SURAJNAV",
                "Ignoring onNavDestinationSelected for MenuItem $name as it cannot be found " +
                        "from the current destination ${navController.currentDestination}",
                e
            )
            false
        }
    }

    fun matchDestination(@IdRes destId: Int, navController: NavController): Boolean =
        navController.graph.hierarchy.any { it.id == destId }

    fun getButtonIdFromString(buttonIdString: String): Int {
        val resources = resources
        return resources.getIdentifier(buttonIdString, "id", packageName)
    }

}