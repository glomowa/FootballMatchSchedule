package com.glomowa.footballmatchschedule.di.component

import com.glomowa.footballmatchschedule.di.module.FragmentModule
import com.glomowa.footballmatchschedule.ui.description.DescriptionFragment
import com.glomowa.footballmatchschedule.ui.description.DescriptionPresenter
import com.glomowa.footballmatchschedule.ui.detailplayer.DetailPlayerActivity
import com.glomowa.footballmatchschedule.ui.favorite.FavoriteFragment
import com.glomowa.footballmatchschedule.ui.favoriteteam.FavoriteTeamFragment
import com.glomowa.footballmatchschedule.ui.listplayer.ListPlayerFragment
import com.glomowa.footballmatchschedule.ui.next.NextFragment
import com.glomowa.footballmatchschedule.ui.prev.PrevFragment
import com.glomowa.footballmatchschedule.ui.team.TeamFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun inject(nextFragment: NextFragment)
    fun inject(prevFragment: PrevFragment)
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(teamFragment: TeamFragment)
    fun inject(descriptionFragment: DescriptionFragment)
    fun inject(listPlayerFragment: ListPlayerFragment)
    fun inject(favoriteTeamFragment: FavoriteTeamFragment)
}