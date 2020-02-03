package com.example.myapplication.screens.questionslist

import android.view.View

interface IQuestionsListViewMvcI {

	interface Listener {
		fun onQuestionClicked(question: Question)
	}

	fun bindQuestions(list: List<Question>)
	fun registersListener(listener: Listener)
	fun unregisterListener(listener: Listener)
	val rootView: View
}
