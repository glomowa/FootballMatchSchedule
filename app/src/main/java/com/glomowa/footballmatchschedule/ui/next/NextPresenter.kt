package com.glomowa.footballmatchschedule.ui.next

import com.glomowa.footballmatchschedule.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NextPresenter: NextContract.Presenter {

    private val subcriptions = CompositeDisposable()
    private val api : ApiService = ApiService.create()
    private lateinit var view: NextContract.View

    override fun loadData(league: String, query: String) {
        var subscribe: Disposable
        view.showProgress(true)
        if (query=="") {
            subscribe = api.getNextEvent(league).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.showProgress(false)
                        view.loadDataSuccess(it.event)
                    }, {
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
        subcriptions.add(subscribe)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subcriptions.clear()
    }

    override fun attach(view: NextContract.View) {
        this.view = view
    }
}