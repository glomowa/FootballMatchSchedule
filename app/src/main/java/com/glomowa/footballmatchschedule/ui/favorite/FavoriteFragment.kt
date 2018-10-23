package com.glomowa.footballmatchschedule.ui.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.db.FavoriteEvent
import com.glomowa.footballmatchschedule.db.database
import com.glomowa.footballmatchschedule.di.component.DaggerFragmentComponent
import com.glomowa.footballmatchschedule.di.module.FragmentModule
import com.glomowa.footballmatchschedule.ui.detailevent.DetailEventActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class FavoriteFragment : Fragment(), FavoriteContract.View {

    @Inject
    lateinit var presenter: FavoriteContract.Presenter
    private lateinit var rootView: View
    private var favorites: MutableList<FavoriteEvent> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
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
        adapter = FavoriteAdapter(favorites){
            intentActivity(it)
        }
        rc_favorite.layoutManager = LinearLayoutManager(context)
        rc_favorite.adapter = adapter
        loadData()
        swipeRefreshFavorite.setOnRefreshListener {
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
            swipeRefreshFavorite.isRefreshing = false
            val result = select(FavoriteEvent.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteEvent>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun intentActivity(favoriteEvent: FavoriteEvent) {
        startActivity<DetailEventActivity>("idHome" to favoriteEvent.idHome, "idAway" to favoriteEvent.idAway, "idEvent" to favoriteEvent.idEvent)
    }

}
