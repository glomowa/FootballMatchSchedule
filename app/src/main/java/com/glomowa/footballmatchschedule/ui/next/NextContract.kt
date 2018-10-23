package com.glomowa.footballmatchschedule.ui.next

import com.glomowa.footballmatchschedule.model.Event
import com.glomowa.footballmatchschedule.ui.base.BaseContract

class NextContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Event>?)
        fun intentDetail(nextEvent: Event)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData(league: String, query: String)
    }
}