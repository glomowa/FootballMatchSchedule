package com.glomowa.footballmatchschedule.ui.favoriteteam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.db.FavoriteTeam
import com.glomowa.footballmatchschedule.model.Team
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.*

class FavoriteTeamAdapter(private val list: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<FavoriteTeamAdapter.FavoriteTeamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamHolder =
            FavoriteTeamHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteTeamHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    class FavoriteTeamHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
            LayoutContainer{
        fun bind(team: FavoriteTeam, listener: (FavoriteTeam) -> Unit){
            item_nameteam.text = team.strTeam
            Glide.with(itemView.context).load(team.strTeamBadge).into(item_logoteam)
            itemView.setOnClickListener {
                listener(team)
            }
        }
    }
}