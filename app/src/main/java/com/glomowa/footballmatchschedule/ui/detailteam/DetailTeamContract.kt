package com.glomowa.footballmatchschedule.ui.detailteam

import com.glomowa.footballmatchschedule.model.Team
import com.glomowa.footballmatchschedule.ui.base.BaseContract

class DetailTeamContract{
    interface View: BaseContract.View{
        fun setPager(team: Team)
        fun addToFavorite()
        fun removeFromFavorite()
        fun favoriteState()
        fun setFavorite()
        fun showSnackbar(message: String)
    }

    interface Presenter: BaseContract.Presenter<View>
}