package com.glomowa.footballmatchschedule

import android.app.Application
import com.glomowa.footballmatchschedule.di.component.ApplicationComponent
import com.glomowa.footballmatchschedule.di.component.DaggerApplicationComponent
import com.glomowa.footballmatchschedule.di.module.ApplicationModule

class BaseApp: Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
        if (BuildConfig.DEBUG){

        }
    }

    private fun setup(){
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}