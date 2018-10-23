package com.glomowa.footballmatchschedule.ui.detailteam

import io.reactivex.disposables.CompositeDisposable

class DetailTeamPresenter: DetailTeamContract.Presenter {

    private val subcriptions = CompositeDisposable()
    private lateinit var view: DetailTeamContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subcriptions.clear()
    }

    override fun attach(view: DetailTeamContract.View) {
        this.view = view
    }
}