package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.widget.ListView
import com.example.myapplication.R
import com.example.myapplication.screens.common.Question


class QuestionsListViewMvcImpl(layoutInflater: LayoutInflater) :
    QuestionsListAdapter.OnQuestionClickListener, IQuestionsListViewMvcI {

    private var questionsListView: ListView
    private var questionsListAdapter : QuestionsListAdapter
    override val rootView: View = layoutInflater.inflate(R.layout.layout_questions_list, null, false)
    private val listeners = mutableListOf<IQuestionsListViewMvcI.Listener>()

    init {
        questionsListView = findViewById(R.id.questions_list_view)
        questionsListAdapter = QuestionsListAdapter(getContext(), this)
        questionsListView.adapter = questionsListAdapter
    }


    private fun <T : View> findViewById(id: Int): T = rootView.findViewById(id)

    private fun getContext() = rootView.context

    override fun onQuestionClickListener(question: Question) {
        for (l in listeners) {
            l.onQuestionClicked(question)
        }
    }

    override fun bindQuestions(list: List<Question>) {
        questionsListAdapter.clear()
        questionsListAdapter.addAll(list)
        questionsListAdapter.notifyDataSetChanged()
    }

    override fun registersListener(listener: IQuestionsListViewMvcI.Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: IQuestionsListViewMvcI.Listener) {
        listeners.remove(listener)
    }
}