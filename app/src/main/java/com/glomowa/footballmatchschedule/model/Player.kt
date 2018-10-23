package com.glomowa.footballmatchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player (var idPlayer: String,
                   var strNationality: String,
                   var strPlayer: String,
                   var strTeam: String,
                   var dateBorn: String,
                   var strDescriptionEN: String,
                   var strPosition: String,
                   var strFanart1: String?,
                   var strCutout: String
) : Parcelable

/*
    "idPlayer": "34145395",
    "strNationality": "England",
    "strPlayer": "Danny Welbeck",
    "strTeam": "Arsenal",
    "dateBorn": "1990-11-26",
    "strDescriptionEN":
    "strPosition": "Forward",
    "strFanart1": "https://www.thesportsdb.com/images/media/player/fanart/wyyyvv1421251691.jpg",
*/
