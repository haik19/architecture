package com.example.myapplication.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.screens.questionslist.QuestionListItemViewMvcImpl
import com.example.myapplication.screens.questionslist.QuestionsListViewMvcImpl

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {
	fun getQuestionListViewMvc(parent: ViewGroup?) = QuestionsListViewMvcImpl(layoutInflater, parent, this)
	fun getQuestionListItemViewMvc (parent: ViewGroup?) = QuestionListItemViewMvcImpl(layoutInflater)
}