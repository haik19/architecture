package com.example.myapplication.screens.common.dependancyinjection

import com.example.myapplication.screens.model.StackOverFlowApiService
import com.example.myapplication.screens.questionslist.FetchQuestionsDetailsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot {
	private var retroFit : Retrofit? = null
	private fun getRetroFit(): Retrofit {
		if (retroFit == null) {
			retroFit = Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("https://api.stackexchange.com/")
				.build()
		}
		return retroFit!!
	}

	fun getStackOverFlowApi(): StackOverFlowApiService = getRetroFit().create(StackOverFlowApiService::class.java)
}