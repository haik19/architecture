package com.example.myapplication.screens.questionslist

import com.example.myapplication.screens.common.BaseObservableViewMvc
import com.example.myapplication.screens.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchQuestionsListUseCase(private val stackOverFlowApiService: StackOverFlowApiService) :
	BaseObservableViewMvc<FetchQuestionsListUseCase.QuestionsListStatusListener>() {

	fun fetchQuestions() {
		stackOverFlowApiService.fetchQuestions(60)
			.enqueue(object : Callback<QuestionsListResponseSchema> {
				override fun onResponse(
					call: Call<QuestionsListResponseSchema>,
					response: Response<QuestionsListResponseSchema>
				) {
					if (response.isSuccessful) {
						response.body()?.let {
							notifySuccess(it)
						}
					}
				}

				override fun onFailure(call: Call<QuestionsListResponseSchema>, t: Throwable) {
					notifyFailed()
				}
			})
	}

	private fun notifySuccess(questionSchema: QuestionsListResponseSchema){
		val questionsList = mutableListOf<Question>()
		questionSchema.list?.forEach {
			questionsList.add(Question(it.id, it.title))
		}
		listeners.forEach {
			it.listFetchedSuccess(questionsList)
		}
	}

	private fun notifyFailed() {
		listeners.forEach {
			it.listFetchedFailed()
		}
	}

	interface QuestionsListStatusListener {
		fun listFetchedSuccess(question : List<Question>)
		fun listFetchedFailed()
	}
}