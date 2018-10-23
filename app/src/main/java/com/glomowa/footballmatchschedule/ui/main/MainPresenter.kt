package com.glomowa.footballmatchschedule.ui.main

import io.reactivex.disposables.CompositeDisposable

class MainPresenter: MainContract.Presenter {

    private val subcriptions = CompositeDisposable()
    private lateinit var view: MainContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subcriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showTeamFragment()
        //default fragment yg ditampilkan
    }

}