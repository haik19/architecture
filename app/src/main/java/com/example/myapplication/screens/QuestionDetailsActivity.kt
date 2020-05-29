package com.example.myapplication.screens

import android.os.Bundle
import com.example.myapplication.screens.common.BaseActivity
import com.example.myapplication.screens.model.QuestionDetails
import com.example.myapplication.screens.questionslist.FetchQuestionsDetailsUseCase
import com.example.myapplication.screens.questionslist.QUESTION_ID_KEY
import com.example.myapplication.screens.questionslist.QuestionDetailsViewMvc


class QuestionDetailsActivity : BaseActivity(), FetchQuestionsDetailsUseCase.Listener {

	private lateinit var detailsViw: QuestionDetailsViewMvc
	private lateinit var fetchQuestionsDetailsUseCase: FetchQuestionsDetailsUseCase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		detailsViw = compositionRoot().getViewMvcFactory().getDetailsViewMvc()
		fetchQuestionsDetailsUseCase = compositionRoot().getFetchQuestionDetailsUseCase()
		setContentView(detailsViw.rootView)
	}

	override fun onStart() {
		super.onStart()
		detailsViw.showProgress()
		fetchQuestionsDetailsUseCase.registersListener(this)
		fetchQuestionsDetailsUseCase.fetchQuestionDetailsAndNotify(intent.getIntExtra(QUESTION_ID_KEY, 0))
	}

	override fun onStop() {
		super.onStop()
		fetchQuestionsDetailsUseCase.unregisterListener( this)
	}

	override fun onQuestionDetailsFetched(questionDetails: QuestionDetails) {
		bindQuestionsDetails(questionDetails)
	}

	override fun onQuestionDetailsFetchFailed() {
		detailsViw.hideProgress()
	}

	private fun bindQuestionsDetails(questionDetails: QuestionDetails) {
		detailsViw.bindQuestionDetails(questionDetails)
		detailsViw.hideProgress()
	}
}