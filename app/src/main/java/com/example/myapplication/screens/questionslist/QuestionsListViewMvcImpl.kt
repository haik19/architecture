package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.screens.model.Question


class QuestionsListViewMvcImpl(layoutInflater: LayoutInflater) :
    QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private var questionsRecyclerView: RecyclerView
    private var muteChannel : TextView
    private var questionsListAdapter : QuestionsListAdapter
    override val rootView: View = layoutInflater.inflate(R.layout.layout_questions_list, null, false)
    private val listeners = mutableListOf<QuestionsListViewMvc.Listener>()

    init {
        muteChannel = findViewById(R.id.mute)
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

    override fun registersListener(listener: QuestionsListViewMvc.Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: QuestionsListViewMvc.Listener) {
        listeners.remove(listener)
    }
}