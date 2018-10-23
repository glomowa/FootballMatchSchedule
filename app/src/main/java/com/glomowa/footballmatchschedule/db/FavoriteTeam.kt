package com.glomowa.footballmatchschedule.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTeam(val idFavorite: Long?,
                        val idTeam: String,
                        val strTeam: String,
                        var strCountry: String,
                        var strTeamBadge: String,
                        var strStadium: String,
                        var intFormedYear: String,
                        var strDescriptionEN: String) : Parcelable {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val FAVORITE_ID: String = "FAVORITE_ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val COUNTRY: String = "COUNTRY"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val STADIUM: String = "STADIUM"
        const val FORMED_YEAR: String = "FORMED_YEAR"
        const val DESCRIPTION: String = "DESCRIPTION"
    }
}