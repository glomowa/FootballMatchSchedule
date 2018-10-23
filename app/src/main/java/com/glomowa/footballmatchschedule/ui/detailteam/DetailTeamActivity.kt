package com.glomowa.footballmatchschedule.ui.detailteam

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.db.FavoriteTeam
import com.glomowa.footballmatchschedule.db.database
import com.glomowa.footballmatchschedule.di.component.DaggerActivityComponent
import com.glomowa.footballmatchschedule.di.module.ActivityModule
import com.glomowa.footballmatchschedule.model.Team
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class DetailTeamActivity : AppCompatActivity(), DetailTeamContract.View {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var team: Team

    @Inject
    lateinit var presenter: DetailTeamContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        injectDependency()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        team = intent.getParcelableExtra<Team>("team")
        collapsing_toolbar.title = team.strTeam
        Glide.with(this).load(team.strTeamBadge).into(backdrop)
        setPager(team)
        favoriteState()
        presenter.attach(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setPager(team: Team) {
        val pageradapter = TabPagerAdapter(supportFragmentManager, team)
        view_pager.adapter = pageradapter
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.setupWithViewPager(view_pager)
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab) {
                view_pager.currentItem = p0.position
            }

            override fun onTabUnselected(p0: TabLayout.Tab) {
            }

            override fun onTabSelected(p0: TabLayout.Tab) {
            }
        })
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
        activityComponent.inject(this)
    }

    override fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_TEAM,
                        FavoriteTeam.TEAM_ID to team.idTeam,
                        FavoriteTeam.TEAM_NAME to team.strTeam,
                        FavoriteTeam.COUNTRY to team.strCountry,
                        FavoriteTeam.TEAM_BADGE to team.strTeamBadge,
                        FavoriteTeam.STADIUM to team.strStadium,
                        FavoriteTeam.FORMED_YEAR to team.intFormedYear,
                        FavoriteTeam.DESCRIPTION to team.strDescriptionEN)
            }
            showSnackbar("Added to Favorite")
        }catch (e: SQLiteConstraintException){
            showSnackbar(e.localizedMessage)
        }
    }

    override fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_TEAM, "("+FavoriteTeam.TEAM_ID+" = {id})", "id" to team.idTeam)
            }
            showSnackbar("Removed from Favorite")
        }catch (e: SQLiteConstraintException){
            showSnackbar(e.localizedMessage)
        }
    }

    override fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_TEAM)
                    .whereArgs("("+FavoriteTeam.TEAM_ID+" = {id})", "id" to team.idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun setFavorite() {
        if (isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    override fun showSnackbar(message: String) {
        snackbar(main_content, message)
    }

}
