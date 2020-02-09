package com.example.myapplication.screens.questionslist

import com.example.myapplication.screens.common.ObservableViewMvc
import com.example.myapplication.screens.model.Question

interface QuestionListItemViewMvc : ObservableViewMvc<QuestionListItemViewMvc.Listener> {
	interface Listener{
		fun onQuestionClicked(question: Question)
	}
	fun bindQuestions(question: Question)
}