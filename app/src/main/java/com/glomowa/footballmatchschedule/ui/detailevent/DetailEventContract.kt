package com.glomowa.footballmatchschedule.ui.detailevent

import com.glomowa.footballmatchschedule.model.Event
import com.glomowa.footballmatchschedule.ui.base.BaseContract

class DetailEventContract {
    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String?)
        fun getDataIntent()
        fun showLogoHome(urlHome: String)
        fun showLogoAway(urlAway: String)
        fun showEventDetail(event: Event?)
        fun setFavorite()
        fun showSnackbar(message: String)
        fun addToFavorite()
        fun removeFromFavorite()
        fun favoriteState()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadDataEvents(idEvent: String)
        fun loadDataTeamHome(idHome: String)
        fun loadDataTeamAway(idAway: String)
    }
}