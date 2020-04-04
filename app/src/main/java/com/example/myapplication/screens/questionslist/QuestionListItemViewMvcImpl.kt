package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.screens.common.BaseObservableViewMvc
import com.example.myapplication.screens.model.Question

class QuestionListItemViewMvcImpl(layoutInflater: LayoutInflater) : BaseObservableViewMvc<QuestionListItemViewMvc.Listener>(),
	QuestionListItemViewMvc {

	override var rootView: View = layoutInflater.inflate(R.layout.question_list_item, null, false)
	private val textTitle: TextView = findViewById(R.id.question_title)

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
}