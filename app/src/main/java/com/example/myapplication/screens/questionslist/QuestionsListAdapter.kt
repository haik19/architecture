package com.example.myapplication.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.myapplication.screens.common.Question

class QuestionsListAdapter(
	context: Context,
	private val onQuestionClickListener: OnQuestionClickListener
) :
	ArrayAdapter<Question>(context, android.R.layout.list_content),
	QuestionListItemViewMVc.Listener {

	override fun onQuestionClicked(question: Question) {
		onQuestionClickListener.onQuestionClickListener(question)
	}

	private val layoutInflater = LayoutInflater.from(context)

	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		val rootView: View
		if (convertView == null) {
			val questionListItemViewMVc: QuestionListItemViewMVc =
				QuestionListItemViewMVcImpl(layoutInflater)
			rootView = questionListItemViewMVc.rootView
			rootView.tag = questionListItemViewMVc
			questionListItemViewMVc.registersListener(this)
		} else {
			rootView = convertView
		}
		getItem(position)?.let {
			(rootView.tag as QuestionListItemViewMVc).bindCusetion(it)
		}
		return rootView
	}

	interface OnQuestionClickListener {
		fun onQuestionClickListener(question: Question)
	}
}


