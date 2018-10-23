package com.glomowa.footballmatchschedule.ui.favoriteteam

import com.glomowa.footballmatchschedule.db.FavoriteTeam
import com.glomowa.footballmatchschedule.ui.base.BaseContract

class FavoriteTeamContract {
    interface View: BaseContract.View{
        fun loadData()
        fun intentActivity(favoriteTeam: FavoriteTeam)
    }

    interface Presenter: BaseContract.Presenter<View>
}