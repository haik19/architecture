package com.example.myapplication.screens

import android.os.Bundle
import android.text.Html
import com.example.myapplication.screens.common.BaseActivity
import com.example.myapplication.screens.model.QuestionDetails
import com.example.myapplication.screens.model.QuestionDetailsResponseSchema
import com.example.myapplication.screens.questionslist.QUESTION_ID_KEY
import com.example.myapplication.screens.questionslist.QuestionDetailsViewMvc
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionDetailsActivity : BaseActivity() {
	private lateinit var detailsViw: QuestionDetailsViewMvc

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val stackOverApi = compositionRoot().getStackOverFlowApi()
		detailsViw = compositionRoot().getViewMvcFactory().getDetailsViewMvc()
		setContentView(detailsViw.rootView)

		val questionId = intent.getIntExtra(QUESTION_ID_KEY, 0)
		stackOverApi.fetchQuestionDetails(questionId)
			.enqueue(object : Callback<QuestionDetailsResponseSchema> {
				override fun onFailure(call: Call<QuestionDetailsResponseSchema>, t: Throwable) {
					detailsViw.hideProgress()
				}

				override fun onResponse(
					call: Call<QuestionDetailsResponseSchema>,
					response: Response<QuestionDetailsResponseSchema>
				) {
					response.takeIf { it.isSuccessful }?.body()?.let {
						if (it.list.isNotEmpty()) {
							it.list[0].apply {
								detailsViw.bindQuestionDetails(QuestionDetails(id, title, body))
							}
						}
					}
					detailsViw.hideProgress()
				}
			})
	}

	override fun onStart() {
		super.onStart()
		detailsViw.showProgress()
	}
}