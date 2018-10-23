package com.glomowa.footballmatchschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayerResponse(@Expose
                          @SerializedName("player")
                          var player: List<Player>)