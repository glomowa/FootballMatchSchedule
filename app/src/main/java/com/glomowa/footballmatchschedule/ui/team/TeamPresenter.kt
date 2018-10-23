package com.glomowa.footballmatchschedule.ui.team

import com.glomowa.footballmatchschedule.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TeamPresenter: TeamContract.Presenter {

    private var api: ApiService = ApiService.create()
    private var subscriptions = CompositeDisposable()
    private lateinit var view: TeamContract.View

    override fun loadData(league: String, query: String) {
        var subscribe: Disposable
        view.showProgress(true)
        if (query=="") {
            subscribe = api.getTeamByLeague(league).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.showProgress(false)
                        view.loadDataSuccess(it.team)
                    }, {
                        view.showProgress(false)
                        view.showErrorMessage(it.localizedMessage)
                    })
        }else{
            subscribe = api.getTeamByName(query).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.showProgress(false)
                        view.loadDataSuccess(it.team)
                    },{
                        view.showProgress(false)
                        view.showErrorMessage(it.localizedMessage)
                    })
        }
        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: TeamContract.View) {
        this.view = view
    }
}