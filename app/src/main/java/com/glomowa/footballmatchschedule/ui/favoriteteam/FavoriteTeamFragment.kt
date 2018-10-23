package com.glomowa.footballmatchschedule.ui.favoriteteam


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.db.FavoriteEvent
import com.glomowa.footballmatchschedule.db.FavoriteTeam
import com.glomowa.footballmatchschedule.db.database
import com.glomowa.footballmatchschedule.di.component.DaggerFragmentComponent
import com.glomowa.footballmatchschedule.di.module.FragmentModule
import com.glomowa.footballmatchschedule.model.Team
import com.glomowa.footballmatchschedule.ui.detailteam.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


class FavoriteTeamFragment : Fragment(), FavoriteTeamContract.View {

    @Inject
    lateinit var presenter: FavoriteTeamContract.Presenter
    private lateinit var rootView: View
    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        adapter = FavoriteTeamAdapter(favorites){
            intentActivity(it)
        }
        rv_favoriteplayer.layoutManager = LinearLayoutManager(context)
        rv_favoriteplayer.adapter = adapter
        loadData()
        swipeRefreshTeam.setOnRefreshListener {
            favorites.clear()
            loadData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun injectDependency(){
        val favComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()
        favComponent.inject(this)
    }

    override fun loadData() {
        context?.database?.use {
            swipeRefreshTeam.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun intentActivity(favoriteTeam: FavoriteTeam) {
        var teammodel = Team(favoriteTeam.idTeam, favoriteTeam.strTeam, favoriteTeam.strCountry, favoriteTeam.strTeamBadge, favoriteTeam.strStadium, favoriteTeam.intFormedYear, favoriteTeam.strDescriptionEN)
        startActivity<DetailTeamActivity>("team" to teammodel)
    }

}
