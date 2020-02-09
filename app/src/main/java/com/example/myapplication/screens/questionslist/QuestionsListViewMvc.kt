package com.example.myapplication.screens.questionslist

import com.example.myapplication.screens.common.ObservableViewMvc
import com.example.myapplication.screens.model.Question

interface QuestionsListViewMvc : ObservableViewMvc<QuestionsListViewMvc.Listener> {
	interface Listener {
		fun onQuestionClicked(question: Question)
		fun mutaChannel()
	}
	fun bindQuestions(list: List<Question>)
}
