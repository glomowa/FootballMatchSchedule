package com.glomowa.footballmatchschedule.db

data class FavoriteEvent(val idFavorite: Long?,
                         val idEvent: String,
                         val idHome: String,
                         val idAway: String,
                         val strAwayTeam: String,
                         val strHomeTeam: String,
                         val strLeague: String) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val FAVORITE_ID: String = "FAVORITE_ID"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val LEAGUE: String = "LEAGUE"
    }
}