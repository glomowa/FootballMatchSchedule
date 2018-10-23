package com.glomowa.footballmatchschedule.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.db.FavoriteEvent
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_favorite.*

class FavoriteAdapter(private val favoriteEvent: List<FavoriteEvent>, private val listener: (FavoriteEvent) -> Unit):
        RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_favorite, p0, false))
    }

    override fun getItemCount(): Int = favoriteEvent.size

    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bind(favoriteEvent[p1], listener)
    }

    class FavoriteViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
            LayoutContainer{
        fun bind(event: FavoriteEvent, listener: (FavoriteEvent) -> Unit){
            item_favoritehome.text = event.strHomeTeam
            item_favoriteaway.text = event.strAwayTeam
            item_favoriteleague.text = event.strLeague
            containerView.setOnClickListener {
                listener(event)
            }
        }
    }
}