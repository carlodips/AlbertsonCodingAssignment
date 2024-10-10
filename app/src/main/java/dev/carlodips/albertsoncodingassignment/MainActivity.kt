package dev.carlodips.albertsoncodingassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.carlodips.albertsoncodingassignment.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var hasBackStackListener: Boolean = false

    private val childFragmentManager: FragmentManager?
        get() = supportFragmentManager.primaryNavigationFragment?.childFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appbar)
        supportActionBar?.elevation = 2f
        setStartingNavGraph()

        supportActionBar?.title = applicationContext.getString(R.string.app_name)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        if (hasBackStackListener) return

        val fm = childFragmentManager ?: return
        fm.addOnBackStackChangedListener {
            if (fm.backStackEntryCount > 0)
                addBackButtonInToolbar()
            else
                removeBackButtonInToolbar()
        }

        if (fm.backStackEntryCount > 0 && binding.appbar.navigationIcon == null)
            addBackButtonInToolbar()

        hasBackStackListener = true
    }

    private fun setStartingNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.mainContent.id) as NavHostFragment
        val navController = navHostFragment.navController

        navController.graph = navController.navInflater.inflate(R.navigation.nav_root)
    }

    private fun addBackButtonInToolbar() {
        with(binding.appbar) {
            navigationIcon = AppCompatResources.getDrawable(
                this@MainActivity,
                R.drawable.ic_arrow_back
            )

            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }
    private fun removeBackButtonInToolbar() {
        with(binding.appbar) {
            navigationIcon = null
            setNavigationOnClickListener(null)
        }
    }
}
