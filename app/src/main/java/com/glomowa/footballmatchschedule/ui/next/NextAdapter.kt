package com.glomowa.footballmatchschedule.ui.next

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.model.Event
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_next.*

class NextAdapter(private val list: MutableList<Event>, private val listener: (Event) -> Unit) :
        RecyclerView.Adapter<NextAdapter.NextHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NextHolder {
        return NextHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_next, p0, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: NextHolder, p1: Int) {
        p0.bind(list[p1], listener)
    }

    class NextHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(item: Event, listener: (Event) -> Unit) {
            item_nexthome.text = item.strHomeTeam
            item_nextaway.text = item.strAwayTeam
            item_nextdatetime.text = item.dateEvent + " " + item.strTime
            item_nextleague.text = item.strLeague
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}