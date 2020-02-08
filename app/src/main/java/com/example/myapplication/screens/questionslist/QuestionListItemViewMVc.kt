package com.example.myapplication.screens.questionslist

import android.view.View
import com.example.myapplication.screens.common.Question

interface QuestionListItemViewMVc {
	interface Listener{
		fun onQuestionClicked(question: Question)
	}
	fun bindCusetion(question: Question)
	fun registersListener(listener: QuestionListItemViewMVc.Listener)
	fun unregisterListener(listener: QuestionListItemViewMVc.Listener)
	val rootView: View
}