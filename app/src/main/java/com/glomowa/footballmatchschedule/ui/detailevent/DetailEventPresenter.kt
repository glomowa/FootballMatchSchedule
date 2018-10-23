package com.glomowa.footballmatchschedule.ui.detailevent

import com.glomowa.footballmatchschedule.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailEventPresenter: DetailEventContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: DetailEventContract.View
    private val api: ApiService = ApiService.create()

    override fun loadDataTeamHome(idHome: String) {
        val subcribe = api.getTeam(idHome).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showProgress(false)
                    view.showLogoHome(it.team[0].strTeamBadge)
                },{
                    view.showProgress(false)
                    view.showErrorMessage(it!!.localizedMessage)
                })
        subscriptions.add(subcribe)
    }

    override fun loadDataTeamAway(idAway: String) {
        val subscribe = api.getTeam(idAway).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showProgress(false)
                    view.showLogoAway(it.team[0].strTeamBadge)
                },{
                    view.showProgress(false)
                    view.showErrorMessage(it!!.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

    override fun loadDataEvents(idEvent: String) {
        val subscribe = api.getEvent(idEvent).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showProgress(false)
                    view.showEventDetail(it.event[0])
                },{
                    view.showProgress(false)
                    view.showErrorMessage(it.localizedMessage)
                })
        subscriptions.add(subscribe)
    }


    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailEventContract.View) {
        this.view = view
    }
}