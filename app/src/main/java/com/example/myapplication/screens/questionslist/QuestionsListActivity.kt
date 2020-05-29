package com.example.myapplication.screens.questionslist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.screens.QuestionDetailsActivity
import com.example.myapplication.screens.common.BaseActivity
import com.example.myapplication.screens.model.Question

const val QUESTION_ID_KEY = "question_id"

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener,FetchQuestionsListUseCase.QuestionsListStatusListener {

	private lateinit var questionsMvcViewImpl: QuestionsListViewMvc
	private lateinit var fetchListsUseCase: FetchQuestionsListUseCase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		questionsMvcViewImpl = compositionRoot().getViewMvcFactory().getQuestionListViewMvc(null)
		questionsMvcViewImpl.registersListener(this)
		fetchListsUseCase = compositionRoot().getFetchQuestionsListUceCase()

		setContentView(questionsMvcViewImpl.rootView)
	}

	override fun onStart() {
		super.onStart()
		fetchListsUseCase.registersListener(this)
		fetchListsUseCase.fetchQuestions()
	}

	override fun onStop() {
		super.onStop()
		fetchListsUseCase.unregisterListener(this)
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

	override fun listFetchedSuccess(question: List<Question>) {
		questionsMvcViewImpl.bindQuestions(question)
	}

	override fun listFetchedFailed() {
		//no need yet
	}
}
