package com.example.myapplication.screens.questionslist

import com.example.myapplication.screens.common.ViewMvc
import com.example.myapplication.screens.model.QuestionDetails

interface QuestionDetailsViewMvc : ViewMvc {
	fun bindQuestionDetails(questionDetails: QuestionDetails)
	fun showProgress()
	fun hideProgress()
}