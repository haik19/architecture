package com.example.myapplication.screens.questionslist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.screens.QuestionDetailsActivity
import com.example.myapplication.screens.common.BaseActivity
import com.example.myapplication.screens.model.Question
import com.example.myapplication.screens.model.QuestionsListResponseSchema
import com.example.myapplication.screens.model.StackOverFlowApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val QUESTION_ID_KEY = "question_id"

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener {

	private lateinit var questionsMvcViewImpl: QuestionsListViewMvc
	private lateinit var stackOverFlowApi: StackOverFlowApiService

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
		stackOverFlowApi.fetchQuestions(60)
			.enqueue(object : Callback<QuestionsListResponseSchema> {
				override fun onResponse(
					call: Call<QuestionsListResponseSchema>,
					response: Response<QuestionsListResponseSchema>
				) {
					if (response.isSuccessful) {
						response.body()?.let {
							bindQuestionsList(it)
						}
					}
				}

				override fun onFailure(call: Call<QuestionsListResponseSchema>, t: Throwable) {
					//no need yet
				}
			})
	}

	fun bindQuestionsList(questionsResponse: QuestionsListResponseSchema) {
		val questionsList = mutableListOf<Question>()
		questionsResponse.list?.forEach {
			questionsList.add(Question(it.id, it.title))
		}
		questionsMvcViewImpl.bindQuestions(questionsList)
	}

	override fun onQuestionClicked(question: Question) {
		Intent(this, QuestionDetailsActivity::class.java).apply {
			putExtra(QUESTION_ID_KEY, question.id)
			startActivity(this)
		}
	}

	override fun mutaChannel() {
		Toast.makeText(this, "Muted!!", Toast.LENGTH_SHORT).show()
	}

	override fun onDestroy() {
		super.onDestroy()
		questionsMvcViewImpl.unregisterListener(this)
	}
}
