package com.example.myapplication.screens.questionslist

import android.view.View
import com.example.myapplication.screens.common.Question

interface QuestionListItemViewMvc {
	interface Listener{
		fun onQuestionClicked(question: Question)
	}
	fun bindQuestions(question: Question)
	fun registersListener(listener: Listener)
	fun unregisterListener(listener: Listener)
	val rootView: View
}