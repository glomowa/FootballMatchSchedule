package com.glomowa.footballmatchschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EventResponse(@Expose
                         @SerializedName("events")
                         var event: List<Event>)