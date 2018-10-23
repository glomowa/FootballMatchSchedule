package com.glomowa.footballmatchschedule.ui.team

import com.glomowa.footballmatchschedule.model.Team
import com.glomowa.footballmatchschedule.ui.base.BaseContract

class TeamContract {
    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Team>?)
        fun intentDetail(team: Team)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadData(league: String, query: String)
    }
}