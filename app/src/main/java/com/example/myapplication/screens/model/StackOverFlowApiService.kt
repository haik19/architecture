package com.example.myapplication.screens.model

import com.example.myapplication.screens.STACK_OVERFLOW_API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackOverFlowApiService {
	@GET("/questions?key=$STACK_OVERFLOW_API_KEY&sort=activity&order=desc&site=stackoverflow&filter=withbody")
	fun fetchQuestions(@Query("pagesize") pageSize: Int): Call<QuestionsListResponseSchema>

    @GET("/questions/{questionId}?key=$STACK_OVERFLOW_API_KEY&site=stackoverflow&filter=withbody")
	fun fetchQuestionDetails(@Path("questionId") questionId: Int): Call<QuestionDetailsResponseSchema>
}