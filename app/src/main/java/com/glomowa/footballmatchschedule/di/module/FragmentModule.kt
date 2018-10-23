package com.glomowa.footballmatchschedule.di.module

import com.glomowa.footballmatchschedule.ui.next.NextContract
import com.glomowa.footballmatchschedule.ui.next.NextPresenter
import com.glomowa.footballmatchschedule.api.ApiService
import com.glomowa.footballmatchschedule.ui.description.DescriptionContract
import com.glomowa.footballmatchschedule.ui.description.DescriptionPresenter
import com.glomowa.footballmatchschedule.ui.favorite.FavoriteContract
import com.glomowa.footballmatchschedule.ui.favorite.FavoritePresenter
import com.glomowa.footballmatchschedule.ui.favoriteteam.FavoriteTeamContract
import com.glomowa.footballmatchschedule.ui.favoriteteam.FavoriteTeamPresenter
import com.glomowa.footballmatchschedule.ui.listplayer.ListPlayerContract
import com.glomowa.footballmatchschedule.ui.listplayer.ListPlayerPresenter
import com.glomowa.footballmatchschedule.ui.prev.PrevContract
import com.glomowa.footballmatchschedule.ui.prev.PrevPresenter
import com.glomowa.footballmatchschedule.ui.team.TeamContract
import com.glomowa.footballmatchschedule.ui.team.TeamPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {
    @Provides
    fun provideNextPresenter(): NextContract.Presenter {
        return NextPresenter()
    }

    @Provides
    fun providePrevPresenter(): PrevContract.Presenter {
        return PrevPresenter()
    }

    @Provides
    fun provideApiService(): ApiService{
        return ApiService.create()
    }

    @Provides
    fun provideFavoritePresenter(): FavoriteContract.Presenter{
        return FavoritePresenter()
    }

    @Provides
    fun provideTeamPresenter(): TeamContract.Presenter {
        return TeamPresenter()
    }

    @Provides
    fun provideDescriptionPresenter(): DescriptionContract.Presenter {
        return DescriptionPresenter()
    }

    @Provides
    fun provideListPlayerPresenter(): ListPlayerContract.Presenter{
        return ListPlayerPresenter()
    }

    @Provides
    fun provideFavoriteTeamPresenter(): FavoriteTeamContract.Presenter{
        return FavoriteTeamPresenter()
    }
}