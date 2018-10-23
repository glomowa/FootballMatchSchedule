package com.glomowa.footballmatchschedule.ui.listplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.model.Player
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_player.*

class ListPlayerAdapter(private val list: List<Player>, private val listener: (Player) -> Unit):
        RecyclerView.Adapter<ListPlayerAdapter.PlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder =
            PlayerHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    class PlayerHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bind(player: Player, listener: (Player) -> Unit){
            item_nameplayer.text = player.strPlayer
            Glide.with(itemView.context).load(player.strCutout).into(item_imageplayer)
            containerView.setOnClickListener {
                listener(player)
            }
        }
    }
}