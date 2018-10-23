package com.glomowa.footballmatchschedule.api

import com.glomowa.footballmatchschedule.BuildConfig
import com.glomowa.footballmatchschedule.model.*
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("lookupteam.php")
    fun getTeam(@Query("id")id: String): Observable<TeamResponse>

    @GET("eventsnextleague.php")
    fun getNextEvent(@Query("id")id: String): Observable<EventResponse>

    @GET("eventspastleague.php?id=4328")
    fun getPastEvent(): Observable<EventResponse>

    @GET("lookupevent.php")
    fun getEvent(@Query("id")id: String): Observable<EventResponse>

    @GET("searchevents.php")
    fun getSearch(@Query("e")e: String): Observable<SearchResponse>

    @GET("search_all_teams.php")
    fun getTeamByLeague(@Query("l")l: String): Observable<TeamResponse>

    @GET("searchteams.php")
    fun getTeamByName(@Query("t")t: String): Observable<TeamResponse>

    @GET("searchplayers.php")
    fun getPlayerByTeam(@Query("t")t: String): Observable<PlayerResponse>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL).build()

            return retrofit.create(ApiService::class.java)
        }
    }

}