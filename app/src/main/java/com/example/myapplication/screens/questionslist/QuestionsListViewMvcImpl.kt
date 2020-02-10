package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.screens.common.BaseViewMvc
import com.example.myapplication.screens.model.Question


class QuestionsListViewMvcImpl(layoutInflater: LayoutInflater) : BaseViewMvc(),
    QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private var questionsRecyclerView: RecyclerView
    private var questionsListAdapter : QuestionsListAdapter
    override var rootView: View = layoutInflater.inflate(R.layout.layout_questions_list, null, false)
    private var muteChannel : TextView = findViewById(R.id.mute)
    private val listeners = mutableListOf<QuestionsListViewMvc.Listener>()

    init {
        muteChannel.setOnClickListener {
            for (l in listeners) {
                l.mutaChannel()
            }
        }

        questionsRecyclerView = findViewById(R.id.questions_list_view)
        questionsRecyclerView.layoutManager = LinearLayoutManager(getContext())
        questionsListAdapter = QuestionsListAdapter(this)
        questionsRecyclerView.adapter = questionsListAdapter
    }

    override fun onQuestionClickListener(question: Question) {
        for (l in listeners) {
            l.onQuestionClicked(question)
        }
    }

    override fun bindQuestions(list: List<Question>) {
        questionsListAdapter.bindQuestions(list)
    }

    override fun registersListener(listener: QuestionsListViewMvc.Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: QuestionsListViewMvc.Listener) {
        listeners.remove(listener)
    }
}