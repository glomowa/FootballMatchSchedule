package com.glomowa.footballmatchschedule.ui.detailplayer

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.di.component.DaggerActivityComponent
import com.glomowa.footballmatchschedule.di.module.ActivityModule
import com.glomowa.footballmatchschedule.model.Player
import kotlinx.android.synthetic.main.activity_detail_player.*
import javax.inject.Inject

class DetailPlayerActivity : AppCompatActivity(), DetailPlayerContract.View {

    @Inject
    lateinit var presenter: DetailPlayerContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        injectDependency()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        presenter.attach(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            if (item.itemId == android.R.id.home){
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun loadData() {
        val player = intent.getParcelableExtra<Player>("player")
        if (player.strFanart1 != null) {
            Glide.with(this).load(player.strFanart1).into(iv_fanart)
        }
        tv_team.text = player.strTeam
        tv_nationality.text = player.strNationality
        tv_position.text = player.strPosition
        tv_descplayer.text = player.strDescriptionEN
        supportActionBar!!.title = player.strPlayer
    }

    private fun injectDependency() {
        val playerComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
        playerComponent.inject(this)
    }

}
