package com.glomowa.footballmatchschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SearchResponse(@Expose
                         @SerializedName("event")
                         var event: List<Event>)