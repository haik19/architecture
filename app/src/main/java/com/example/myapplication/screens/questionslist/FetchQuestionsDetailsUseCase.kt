package com.example.myapplication.screens.questionslist

import com.example.myapplication.screens.common.BaseObservableViewMvc
import com.example.myapplication.screens.model.QuestionDetails
import com.example.myapplication.screens.model.QuestionDetailsResponseSchema
import com.example.myapplication.screens.model.StackOverFlowApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchQuestionsDetailsUseCase(private val stackOverflowApi: StackOverFlowApiService) :
	BaseObservableViewMvc<FetchQuestionsDetailsUseCase.Listener>() {

	interface Listener {
		fun onQuestionDetailsFetched(questionDetails: QuestionDetails)
		fun onQuestionDetailsFetchFailed()
	}

	fun fetchQuestionDetailsAndNotify(questionId: Int) {
		stackOverflowApi.fetchQuestionDetails(questionId)
			.enqueue(object : Callback<QuestionDetailsResponseSchema> {
				override fun onFailure(call: Call<QuestionDetailsResponseSchema>, t: Throwable) {
					notifyFailure()
				}

				override fun onResponse(
					call: Call<QuestionDetailsResponseSchema>,
					response: Response<QuestionDetailsResponseSchema>
				) {
					if (response.isSuccessful) {
						response.body()?.let {
							notifySuccess(it)
						}
					}
				}
			})
	}

	fun notifySuccess(response: QuestionDetailsResponseSchema) {
		listeners.forEach {
			response.apply {
				if (list.isNotEmpty()) {
					list[0].apply {
						it.onQuestionDetailsFetched(QuestionDetails(id, title, body))
					}
				}
			}
		}
	}

	fun notifyFailure() {
		listeners.forEach {
			it.onQuestionDetailsFetchFailed()
		}
	}
}