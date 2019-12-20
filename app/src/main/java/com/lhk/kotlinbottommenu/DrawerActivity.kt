package com.lhk.kotlinbottommenu

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.drawer_content.*

class DrawerActivity :AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        setSupportActionBar(toolbar)

        fab.setOnClickListener(OnFabClickListener(this))

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home
                , R.id.navigation_dashboard
                , R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)
    }

    private fun showMainActivity(){
        val intent = Intent(this,MainActivity().javaClass)
        startActivity(intent)
    }

    private fun showMainPullActivity(){
        val intent = Intent(this,MainPullActivity().javaClass)
        startActivity(intent)
    }

    private fun showLoginActivity(){
        val intent = Intent(this,LoginActivity().javaClass)
        startActivity(intent)
    }

    private fun showGuideActivity(){
        val intent = Intent(this,GuideActivity().javaClass)
        startActivity(intent)
    }

    private var mBottomSheetDialog: BottomSheetDialog? = null
    private fun showBottomSheetDialog(){
        val sheetDialogView = View.inflate(this,R.layout.sheet_dialog,null)
        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog!!.setContentView(sheetDialogView)
        mBottomSheetDialog!!.setOnDismissListener(DialogInterface.OnDismissListener { mBottomSheetDialog=null })
        mBottomSheetDialog!!.show()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                showBottomSheetDialog()
                return true
            }
            R.id.action_login -> {
                showLoginActivity()
                return true
            }
            R.id.action_guide -> {
                showGuideActivity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {
                showMainActivity()
            }
            R.id.nav_gallery -> {
                showMainPullActivity()
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    class OnFabClickListener(context: Context): View.OnClickListener {
        override fun onClick(view: View?) {
            if (view != null) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }
}