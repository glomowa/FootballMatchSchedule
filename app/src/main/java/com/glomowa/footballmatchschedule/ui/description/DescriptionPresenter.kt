package com.glomowa.footballmatchschedule.ui.description

import io.reactivex.disposables.CompositeDisposable

class DescriptionPresenter: DescriptionContract.Presenter {

    private var subscription = CompositeDisposable()
    private lateinit var view: DescriptionContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscription.clear()
    }

    override fun attach(view: DescriptionContract.View) {
        this.view = view
        view.loadData()
    }
}