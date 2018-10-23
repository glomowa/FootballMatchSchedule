package com.glomowa.footballmatchschedule.di.component

import dagger.Component
import com.glomowa.footballmatchschedule.BaseApp
import com.glomowa.footballmatchschedule.di.module.ApplicationModule

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(app: BaseApp)
}