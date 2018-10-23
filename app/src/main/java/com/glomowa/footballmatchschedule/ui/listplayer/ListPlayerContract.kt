package com.glomowa.footballmatchschedule.ui.listplayer

import com.glomowa.footballmatchschedule.model.Player
import com.glomowa.footballmatchschedule.ui.base.BaseContract

class ListPlayerContract {
    interface View: BaseContract.View{
        fun getDataIntent()
        fun showProgress(show: Boolean)
        fun loadDataSuccess(list: List<Player>)
        fun showErrorMessage(message: String)
        fun intentDetail(player: Player)
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun loadPlayer(team: String)
    }
}