package com.glomowa.footballmatchschedule.ui.favorite

import io.reactivex.disposables.CompositeDisposable

class FavoritePresenter: FavoriteContract.Presenter {
    private val subcriptions = CompositeDisposable()
    private lateinit var view: FavoriteContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subcriptions.clear()
    }

    override fun attach(view: FavoriteContract.View) {
        this.view = view
    }

}