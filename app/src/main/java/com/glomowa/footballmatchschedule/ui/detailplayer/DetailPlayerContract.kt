package com.glomowa.footballmatchschedule.ui.detailplayer

import com.glomowa.footballmatchschedule.ui.base.BaseContract

class DetailPlayerContract {
    interface View: BaseContract.View{
        fun loadData()
    }

    interface Presenter: BaseContract.Presenter<View>
}