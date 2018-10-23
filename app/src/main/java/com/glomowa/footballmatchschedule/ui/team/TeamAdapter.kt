package com.glomowa.footballmatchschedule.ui.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.model.Team
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.*

class TeamAdapter(private val list: List<Team>, private val listener: (Team) -> Unit ):
        RecyclerView.Adapter<TeamAdapter.TeamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        return TeamHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    class TeamHolder(override var containerView: View): RecyclerView.ViewHolder(containerView),
            LayoutContainer{
        fun bind(team: Team, listener: (Team) -> Unit){
            item_nameteam.text = team.strTeam
            Glide.with(itemView.context).load(team.strTeamBadge).into(item_logoteam)
            itemView.setOnClickListener {
                listener(team)
            }
        }
    }
}