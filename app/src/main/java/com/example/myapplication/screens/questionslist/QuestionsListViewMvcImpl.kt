package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.screens.common.Question


class QuestionsListViewMvcImpl(layoutInflater: LayoutInflater) :
    QuestionsListAdapter.OnQuestionClickListener, IQuestionsListViewMvcI {

    private var questionsRecyclerView: RecyclerView
    private var questionsListAdapter : QuestionsListAdapter
    override val rootView: View = layoutInflater.inflate(R.layout.layout_questions_list, null, false)
    private val listeners = mutableListOf<IQuestionsListViewMvcI.Listener>()

    init {
        questionsRecyclerView = findViewById(R.id.questions_list_view)
        questionsRecyclerView.layoutManager = LinearLayoutManager(getContext())
        questionsListAdapter = QuestionsListAdapter(this)
        questionsRecyclerView.adapter = questionsListAdapter
    }

    private fun <T : View> findViewById(id: Int): T = rootView.findViewById(id)

    private fun getContext() = rootView.context

    override fun onQuestionClickListener(question: Question) {
        for (l in listeners) {
            l.onQuestionClicked(question)
        }
    }

    override fun bindQuestions(list: List<Question>) {
        questionsListAdapter.bindQuestions(list)
    }

    override fun registersListener(listener: IQuestionsListViewMvcI.Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: IQuestionsListViewMvcI.Listener) {
        listeners.remove(listener)
    }
}