package com.glomowa.footballmatchschedule.di.module

import android.app.Activity
import com.glomowa.footballmatchschedule.ui.detailevent.DetailEventContract
import com.glomowa.footballmatchschedule.ui.detailevent.DetailEventPresenter
import com.glomowa.footballmatchschedule.ui.detailplayer.DetailPlayerContract
import com.glomowa.footballmatchschedule.ui.detailplayer.DetailPlayerPresenter
import com.glomowa.footballmatchschedule.ui.detailteam.DetailTeamContract
import com.glomowa.footballmatchschedule.ui.detailteam.DetailTeamPresenter
import com.glomowa.footballmatchschedule.ui.main.MainContract
import com.glomowa.footballmatchschedule.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {
    @Provides
    fun provideActivity():Activity{
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    @Provides
    fun provideDetailPresenter(): DetailEventContract.Presenter{
        return DetailEventPresenter()
    }

    @Provides
    fun provideDetailTeamPresenter(): DetailTeamContract.Presenter{
        return DetailTeamPresenter()
    }

    @Provides
    fun provideDetailPlayerPresenter(): DetailPlayerContract.Presenter{
        return DetailPlayerPresenter()
    }
}