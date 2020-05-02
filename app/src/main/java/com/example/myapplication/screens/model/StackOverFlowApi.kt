package com.example.myapplication.screens.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverFlowApi{
    @GET("/2.2/questions")
    fun getQuestions(@Query("site") site : String): Call<QuestionsResponse>
}