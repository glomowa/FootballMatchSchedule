package com.glomowa.footballmatchschedule.ui.prev

import com.glomowa.footballmatchschedule.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PrevPresenter : PrevContract.Presenter {

    private val subcripstion = CompositeDisposable()
    private val api: ApiService = ApiService.create()
    private lateinit var view: PrevContract.View

    override fun loadData(league: String, query: String) {
        var subscribe: Disposable
        view.showProgress(true)
        if (query==""){
            subscribe = api.getPastEvent().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.showProgress(false)
                        view.loadDataSuccess(it.event)
                    },{
                        view.showProgress(false)
                        view.showErrorMessage(it.localizedMessage)
                    })
        }else{
            subscribe = api.getSearch(query).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.showProgress(false)
                        view.loadDataSuccess(it.event)
                    },{
                        view.showProgress(false)
                        view.showErrorMessage(it.localizedMessage)
                    })
        }
        subcripstion.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subcripstion.clear()
    }

    override fun attach(view: PrevContract.View) {
        this.view = view
    }

}