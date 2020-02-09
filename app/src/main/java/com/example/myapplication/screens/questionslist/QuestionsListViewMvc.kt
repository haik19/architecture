package com.example.myapplication.screens.questionslist

import com.example.myapplication.screens.common.ViewMvc
import com.example.myapplication.screens.model.Question

interface QuestionsListViewMvc : ViewMvc {

	interface Listener {
		fun onQuestionClicked(question: Question)
		fun mutaChannel()
	}

	fun bindQuestions(list: List<Question>)
	fun registersListener(listener: Listener)
	fun unregisterListener(listener: Listener)
}
