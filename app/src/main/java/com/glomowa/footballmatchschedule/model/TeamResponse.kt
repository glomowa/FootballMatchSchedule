package com.glomowa.footballmatchschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TeamResponse(@Expose
                        @SerializedName("teams")
                        val team: List<Team>)