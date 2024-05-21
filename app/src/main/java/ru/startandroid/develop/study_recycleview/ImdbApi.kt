package ru.startandroid.develop.study_recycleview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImdbApi {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun getLocations(
        @Path("expression") expression: String
    ): Call<ImdbResponse>
}