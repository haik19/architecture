package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.screens.common.BaseViewMvc
import com.example.myapplication.screens.model.Question

class QuestionListItemViewMvcImpl(layoutInflater: LayoutInflater) : BaseViewMvc(),
	QuestionListItemViewMvc {

	override var rootView: View = layoutInflater.inflate(R.layout.question_list_item, null, false)
	private val textTitle: TextView = findViewById(R.id.question_title)
	private val listeners = mutableListOf<QuestionListItemViewMvc.Listener>()

	private lateinit var question: Question
	init {
		rootView.setOnClickListener {
			for (l in listeners){
				l.onQuestionClicked(question)
			}
		}
	}

	override fun bindQuestions(question: Question) {
		this.question = question
		textTitle.text = question.title
	}

	override fun registersListener(listener: QuestionListItemViewMvc.Listener) {
		listeners.add(listener)
	}

	override fun unregisterListener(listener: QuestionListItemViewMvc.Listener) {
		listeners.remove(listener)
	}

}