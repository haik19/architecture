package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.screens.common.Question

class QuestionListItemViewMVcImpl(layoutInflater: LayoutInflater) : QuestionListItemViewMVc{

	override val rootView: View = layoutInflater.inflate(R.layout.question_list_item, null, false)
	private val textTitle: TextView = findViewById(R.id.question_title)
	private val listeners = mutableListOf<QuestionListItemViewMVc.Listener>()

	private lateinit var question: Question
	init {
		rootView.setOnClickListener {
			for (l in listeners){
				l.onQuestionClicked(question)
			}
		}
	}

	override fun bindCusetion(question: Question) {
		this.question = question
		textTitle.text = question.title
	}

	override fun registersListener(listener: QuestionListItemViewMVc.Listener) {
		listeners.add(listener)
	}

	override fun unregisterListener(listener: QuestionListItemViewMVc.Listener) {
		listeners.remove(listener)
	}

	private fun <T : View> findViewById(id : Int): T = rootView.findViewById(id)

}