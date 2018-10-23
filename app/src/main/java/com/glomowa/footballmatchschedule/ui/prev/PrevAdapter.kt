package com.glomowa.footballmatchschedule.ui.prev

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.model.Event
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_prev.*

class PrevAdapter(private val list: MutableList<Event>, private val listener: (Event) -> Unit):
    RecyclerView.Adapter<PrevAdapter.PrevHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PrevHolder {
        return PrevHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_prev, p0, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: PrevHolder, p1: Int) {
        p0.bind(list[p1], listener)
    }

    class PrevHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
            LayoutContainer{
        fun bind(pastEvent: Event, listener: (Event) -> Unit){
            item_prevaway.text = pastEvent.strAwayTeam
            item_prevhome.text = pastEvent.strHomeTeam
            item_prevdatetime.text = pastEvent.dateEvent + " " + pastEvent.strTime
            item_prevscoreaway.text = pastEvent.intAwayScore
            item_prevscorehome.text = pastEvent.intHomeScore
            item_prevleague.text = pastEvent.strLeague
            itemView.setOnClickListener {
                listener(pastEvent)
            }
        }
    }
}