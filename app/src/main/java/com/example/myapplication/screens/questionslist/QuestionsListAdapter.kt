package com.example.myapplication.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R

class QuestionsListAdapter(context: Context, private val onQuestionClickListener: OnQuestionClickListener) :
    ArrayAdapter<Question>(context, android.R.layout.list_content) {

    private val layoutInflater= LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView: View
        if (convertView == null) {
            rootView = layoutInflater.inflate(
                R.layout.question_list_item,
                parent,
                false
            )
            val questionsViewHolder = QuestionsViewHolder()
            questionsViewHolder.textView = rootView.findViewById(R.id.question_title)
            rootView.tag = questionsViewHolder
        } else {
            rootView = convertView
        }
        val question = getItem(position)
        (rootView.tag as QuestionsViewHolder).textView?.text = question?.title
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

    class QuestionsViewHolder {
        var textView : TextView? = null
    }
}


