package com.glomowa.footballmatchschedule.di.module

import android.app.Application
import com.glomowa.footballmatchschedule.BaseApp
import com.glomowa.footballmatchschedule.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application{
        return baseApp
    }
}