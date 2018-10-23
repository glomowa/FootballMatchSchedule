package com.glomowa.footballmatchschedule.ui.detailplayer

import com.glomowa.footballmatchschedule.ui.base.BaseContract
import io.reactivex.disposables.CompositeDisposable

class DetailPlayerPresenter: DetailPlayerContract.Presenter{

    private var subscriptions = CompositeDisposable()
    private lateinit var view: DetailPlayerContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailPlayerContract.View) {
        this.view = view
        view.loadData()
    }

}