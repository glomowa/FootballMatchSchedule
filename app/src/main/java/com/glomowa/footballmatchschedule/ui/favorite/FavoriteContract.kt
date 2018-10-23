package com.glomowa.footballmatchschedule.ui.favorite

import com.glomowa.footballmatchschedule.db.FavoriteEvent
import com.glomowa.footballmatchschedule.ui.base.BaseContract

class FavoriteContract {
    interface View : BaseContract.View{
        fun loadData()
        fun intentActivity(favoriteEvent: FavoriteEvent)
    }

    interface Presenter: BaseContract.Presenter<View>
}