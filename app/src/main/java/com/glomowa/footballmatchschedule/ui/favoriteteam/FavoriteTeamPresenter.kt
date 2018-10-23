package com.glomowa.footballmatchschedule.ui.favoriteteam

import io.reactivex.disposables.CompositeDisposable

class FavoriteTeamPresenter: FavoriteTeamContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: FavoriteTeamContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: FavoriteTeamContract.View) {
        this.view = view
    }

}