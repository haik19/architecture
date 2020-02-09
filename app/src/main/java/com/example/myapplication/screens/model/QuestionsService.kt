package com.example.myapplication.screens.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionsService {
    @GET("/2.2/questions")
    fun getQuestions(@Query("site") site : String): Call<QuestionsResponse>
}