package com.glomowa.footballmatchschedule.di.component

import com.glomowa.footballmatchschedule.di.module.ActivityModule
import com.glomowa.footballmatchschedule.ui.detailevent.DetailEventActivity
import com.glomowa.footballmatchschedule.ui.detailplayer.DetailPlayerActivity
import com.glomowa.footballmatchschedule.ui.detailteam.DetailTeamActivity
import com.glomowa.footballmatchschedule.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(detailEventActivity: DetailEventActivity)
    fun inject(detailTeamActivity: DetailTeamActivity)
    fun inject(detailPlayerActivity: DetailPlayerActivity)
}