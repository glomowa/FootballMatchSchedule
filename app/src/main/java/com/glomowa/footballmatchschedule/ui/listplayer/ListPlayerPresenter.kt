package com.glomowa.footballmatchschedule.ui.listplayer

import com.glomowa.footballmatchschedule.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListPlayerPresenter: ListPlayerContract.Presenter {

    private var api: ApiService = ApiService.create()
    private lateinit var view: ListPlayerContract.View
    private var subscription = CompositeDisposable()

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscription.clear()
    }

    override fun attach(view: ListPlayerContract.View) {
        this.view = view
        view.getDataIntent()
    }

    override fun loadPlayer(team: String) {
        view.showProgress(true)
        var subscribe = api.getPlayerByTeam(team).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showProgress(false)
                    view.loadDataSuccess(it.player)
                },{
                    view.showProgress(false)
                    view.showErrorMessage(it.localizedMessage)
                })
        subscription.add(subscribe)
    }
}