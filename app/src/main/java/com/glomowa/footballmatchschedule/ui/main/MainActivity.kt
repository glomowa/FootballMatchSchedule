package com.glomowa.footballmatchschedule.ui.main

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.ui.team.TeamFragment
import com.glomowa.footballmatchschedule.di.component.DaggerActivityComponent
import com.glomowa.footballmatchschedule.di.module.ActivityModule
import com.glomowa.footballmatchschedule.ui.favorite.FavoriteFragment
import com.glomowa.footballmatchschedule.ui.favoriteteam.FavoriteTeamFragment
import com.glomowa.footballmatchschedule.ui.next.NextFragment
import com.glomowa.footballmatchschedule.ui.prev.PrevFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        title = "Football Team"

        nav_view.setNavigationItemSelectedListener(this)
        injectDependency()
        presenter.attach(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.nav_next -> {
                title = "Next Matches"
                showNextFragment()
            }
            R.id.nav_prev -> {
                title = "Previous Matches"
                showPrevFragment()
            }
            R.id.nav_team -> {
                title = "Football Teams"
                showTeamFragment()
            }
            R.id.nav_favorite -> {
                title = "Favorite Matches"
                showFavoriteFragment()
            }
            R.id.nav_favoriteteam -> {
                title = "Favorite Teams"
                showFavoriteTeamFragment()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showNextFragment() {
        supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.contentmain, NextFragment())
                .commit()
    }

    override fun showPrevFragment() {
        supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.contentmain, PrevFragment())
                .commit()
    }

    override fun showFavoriteFragment() {
        supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.contentmain, FavoriteFragment())
                .commit()
    }

    override fun showTeamFragment() {
        supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.contentmain, TeamFragment())
                .commit()
    }

    override fun showFavoriteTeamFragment() {
        supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.contentmain, FavoriteTeamFragment())
                .commit()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
        activityComponent.inject(this)
    }

}
