package com.example.myapplication.screens.questionslist

import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.screens.common.BaseActivity
import com.example.myapplication.screens.model.Question
import com.example.myapplication.screens.model.QuestionsResponse
import com.example.myapplication.screens.model.StackOverFlowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener {

	private lateinit var questionsMvcViewImpl: QuestionsListViewMvc
	private lateinit var stackOverFlowApi: StackOverFlowApi

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		questionsMvcViewImpl = compositionRoot().getViewMvcFactory().getQuestionListViewMvc(null)
		questionsMvcViewImpl.registersListener(this)

		stackOverFlowApi = compositionRoot().getStackOverFlowApi()

		setContentView(questionsMvcViewImpl.rootView)
    }

	override fun onStart() {
		super.onStart()
		fetchQuestions()
	}

	private fun fetchQuestions() {
		stackOverFlowApi.getQuestions("stackoverflow")
			.enqueue(object : Callback<QuestionsResponse> {
				override fun onResponse(
					call: Call<QuestionsResponse>,
					response: Response<QuestionsResponse>
				) {
					if (response.isSuccessful) {
						response.body()?.let {
							bindQuestionsList(it)
						}
					}
				}

				override fun onFailure(call: Call<QuestionsResponse>, t: Throwable) {
					//no need yet
				}
			})
	}

	fun bindQuestionsList(questionsResponse: QuestionsResponse) {
		questionsMvcViewImpl.bindQuestions(questionsResponse.list)
	}

	override fun onQuestionClicked(question: Question) {
		Toast.makeText(this, question.title, Toast.LENGTH_SHORT).show()
	}

	override fun mutaChannel() {
		Toast.makeText(this, "Muted!!", Toast.LENGTH_SHORT).show()
	}

	override fun onDestroy() {
		super.onDestroy()
		questionsMvcViewImpl.unregisterListener(this)
	}
}
