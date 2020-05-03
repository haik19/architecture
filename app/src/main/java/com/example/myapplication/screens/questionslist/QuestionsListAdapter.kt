package com.example.myapplication.screens.questionslist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.screens.common.ViewMvcFactory
import com.example.myapplication.screens.model.Question

class QuestionsListAdapter(
	private val onQuestionClickListener: OnQuestionClickListener,
	val viewMvcFactory: ViewMvcFactory
) :
	RecyclerView.Adapter<QuestionsListAdapter.QuestionViewHolder>(), QuestionListItemViewMvc.Listener {

	private var questionsList = mutableListOf<Question>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
		val questionListItemViewMVc  = viewMvcFactory.getQuestionListItemViewMvc(parent)
		questionListItemViewMVc.registersListener(this)
		return QuestionViewHolder(questionListItemViewMVc)
	}

	override fun getItemCount() = questionsList.size

	override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
		holder.questionMvcView.bindQuestions(questionsList[position])
	}

	override fun onQuestionClicked(question: Question) {
		onQuestionClickListener.onQuestionClickListener(question)
	}

	fun bindQuestions(list : List<Question>) {
		questionsList.addAll(list)
		notifyDataSetChanged()
	}

	interface OnQuestionClickListener {
		fun onQuestionClickListener(question: Question)
	}

	class QuestionViewHolder(val questionMvcView : QuestionListItemViewMvc) : RecyclerView.ViewHolder(questionMvcView.rootView)
}


