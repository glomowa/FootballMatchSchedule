package com.glomowa.footballmatchschedule.ui.next

import com.glomowa.footballmatchschedule.api.ApiService
import com.glomowa.footballmatchschedule.model.Event
import com.glomowa.footballmatchschedule.model.EventResponse
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class NextPresenterTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private
    lateinit var view: NextContract.View

    @Mock
    private
    val api: ApiService = ApiService.create()

    private lateinit var presenter: NextPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextPresenter()
    }

    @Test
    fun loadData() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        presenter.attach(view)
        `when`(api.getNextEvent("4328")).thenReturn(Observable.just(response))
        presenter.loadData("4328","")

        verify(view).showProgress(true)
        verify(view).showProgress(false)

    }

    class RxImmediateSchedulerRule : TestRule {
        override fun apply(base: Statement?, description: org.junit.runner.Description?): Statement? {
            return object : Statement() {
                @Throws(Throwable::class)
                override fun evaluate() {
                    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                    RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
                    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

                    try {
                        base?.evaluate()
                    } finally {
                        RxJavaPlugins.reset()
                        RxAndroidPlugins.reset()
                    }
                }
            }
        }
    }
}
