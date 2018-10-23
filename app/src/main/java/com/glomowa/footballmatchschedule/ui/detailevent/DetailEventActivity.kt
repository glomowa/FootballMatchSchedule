package com.glomowa.footballmatchschedule.ui.detailevent

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.R.id.add_to_favorite
import com.glomowa.footballmatchschedule.R.menu.detail_menu
import com.glomowa.footballmatchschedule.db.FavoriteEvent
import com.glomowa.footballmatchschedule.db.database
import com.glomowa.footballmatchschedule.di.component.DaggerActivityComponent
import com.glomowa.footballmatchschedule.di.module.ActivityModule
import com.glomowa.footballmatchschedule.model.Event
import kotlinx.android.synthetic.main.activity_detail_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class DetailEventActivity : AppCompatActivity(), DetailEventContract.View {

    @Inject
    lateinit var presenter: DetailEventContract.Presenter
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private var idEvent: String = ""
    private var idHome: String = ""
    private var idAway: String = ""
    private lateinit var eventx: Event
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)
        supportActionBar?.title = "Detail Event"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        dialog = builder.setView(dialogView).setCancelable(false).create()
        injectDependency()
        presenter.attach(this)
        presenter.subscribe()
        getDataIntent()
        favoriteState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
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
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
        activityComponent.inject(this)
    }

    override fun showProgress(show: Boolean) {
        if (show){
            dialog.show()
        }else{
            dialog.hide()
        }
    }

    override fun showErrorMessage(error: String?) {
        if (error != null) {
            error(error)
        }
    }

    override fun showLogoHome(urlHome: String) {
        Glide.with(this).load(urlHome).into(detail_imghome)
    }

    override fun showLogoAway(urlAway: String) {
        Glide.with(this).load(urlAway).into(detail_imgaway)
    }

    override fun getDataIntent() {
        idHome = intent.getStringExtra("idHome")
        idAway = intent.getStringExtra("idAway")
        idEvent = intent.getStringExtra("idEvent")
        presenter.loadDataEvents(idEvent)
        presenter.loadDataTeamAway(idAway)
        presenter.loadDataTeamHome(idHome)
    }

    override fun showEventDetail(event: Event?) {
        detail_league.text = event?.strLeague
        detail_time.text = event?.dateEvent+" "+event?.strTime
        detail_namehome.text = event?.strHomeTeam
        detail_scorehome.text = event?.intHomeScore
        detail_nameaway.text = event?.strAwayTeam
        detail_scoreaway.text = event?.intAwayScore
        detail_goalhome.text = event?.strHomeGoalDetails
        detail_goalaway.text = event?.strAwayGoalDetails
        detail_redhome.text = event?.strHomeRedCards
        detail_redaway.text = event?.strAwayRedCards
        detail_yellowhome.text = event?.strHomeYellowCards
        detail_yellowaway.text = event?.strAwayYellowCards
        if (event!=null) eventx = event
    }

    override fun setFavorite() {
        if (isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    override fun showSnackbar(message: String) {
        snackbar(layoutDetail, message)
    }

    override fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteEvent.TABLE_FAVORITE,
                        FavoriteEvent.EVENT_ID to idEvent,
                        FavoriteEvent.HOME_ID to idHome,
                        FavoriteEvent.AWAY_ID to idAway,
                        FavoriteEvent.HOME_TEAM to eventx.strHomeTeam,
                        FavoriteEvent.AWAY_TEAM to eventx.strAwayTeam,
                        FavoriteEvent.LEAGUE to eventx.strLeague)
            }
            showSnackbar("Added to Favorite")
        }catch (e: SQLiteConstraintException){
            showSnackbar(e.localizedMessage)
        }
    }

    override fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteEvent.TABLE_FAVORITE, "("+FavoriteEvent.EVENT_ID+" = {id})", "id" to idEvent)
            }
            showSnackbar("Removed from Favorite")
        }catch (e: SQLiteConstraintException){
            showSnackbar(e.localizedMessage)
        }
    }

    override fun favoriteState(){
        database.use {
            val result = select(FavoriteEvent.TABLE_FAVORITE)
                    .whereArgs("("+FavoriteEvent.EVENT_ID+" = {id})", "id" to idEvent)
            val favorite = result.parseList(classParser<FavoriteEvent>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
