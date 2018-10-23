package com.glomowa.footballmatchschedule.ui.description

import com.glomowa.footballmatchschedule.ui.base.BaseContract

class DescriptionContract {
    interface View: BaseContract.View{
        fun loadData()
    }

    interface Presenter: BaseContract.Presenter<View>{

    }
}