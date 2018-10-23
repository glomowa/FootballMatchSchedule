package com.glomowa.footballmatchschedule.ui.main

import com.glomowa.footballmatchschedule.ui.base.BaseContract

class MainContract {
    interface View: BaseContract.View {
        fun showNextFragment()
        fun showPrevFragment()
        fun showFavoriteFragment()
        fun showTeamFragment()
        fun showFavoriteTeamFragment()
    }

    interface Presenter: BaseContract.Presenter<View>
}