package com.example.myapplication.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class QuestionsListAdapter(context: Context, val onQuestionClickListener: OnQuestionClickListener) :
    ArrayAdapter<Question>(context, android.R.layout.list_content) {

    private val layoutInflater= LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        val question = getItem(position)
        (rootView as TextView).text = question?.title
        rootView.setOnClickListener {
            question?.let {
                onQuestionClickListener.onQuestionClickListener(it)
            }
        }
        return rootView
    }

    interface OnQuestionClickListener {
        fun onQuestionClickListener(question: Question)
    }
}


