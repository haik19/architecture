package com.example.myapplication.screens.questionslist

import com.example.myapplication.screens.common.ViewMvc
import com.example.myapplication.screens.model.Question

interface QuestionListItemViewMvc : ViewMvc {
	interface Listener{
		fun onQuestionClicked(question: Question)
	}
	fun bindQuestions(question: Question)
	fun registersListener(listener: Listener)
	fun unregisterListener(listener: Listener)
}