package ru.startandroid.develop.study_recycleview

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

class TranslationResponse(val contents: Content) {

    data class Content(val translated: String,
                       val text: String,
                       val translation: String)

    interface TranslationApi {

        @POST("/translate/yoda.json")
        fun translateToYoda(@Body request: TranslationRequest): Call<TranslationResponse>

        @POST("/translate/morse.json")
        fun translateToMorse(@Body request: TranslationRequest): Call<TranslationResponse>
    }
}