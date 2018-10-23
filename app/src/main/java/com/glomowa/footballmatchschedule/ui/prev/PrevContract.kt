package com.glomowa.footballmatchschedule.ui.prev

import com.glomowa.footballmatchschedule.model.Event
import com.glomowa.footballmatchschedule.ui.base.BaseContract

class PrevContract {
    interface View: BaseContract.View{
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Event>?)
        fun intentDetail(event: Event)
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun loadData(league: String, query: String)
    }
}