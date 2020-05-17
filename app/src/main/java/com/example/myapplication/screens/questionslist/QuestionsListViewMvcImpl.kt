package com.example.myapplication.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.screens.common.BaseObservableViewMvc
import com.example.myapplication.screens.common.ViewMvcFactory
import com.example.myapplication.screens.model.Question


class QuestionsListViewMvcImpl(
    layoutInflater: LayoutInflater,
    val parent: View?,
    val viewMvcFactory: ViewMvcFactory
) : BaseObservableViewMvc<QuestionsListViewMvc.Listener>(),
    QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private var questionsRecyclerView: RecyclerView
    private var questionsListAdapter : QuestionsListAdapter
    override var rootView: View = layoutInflater.inflate(R.layout.layout_questions_list, null, false)
    private var muteChannel : TextView = findViewById(R.id.mute)

    init {
        muteChannel.setOnClickListener {
            for (l in listeners) {
                l.mutaChannel()
            }
        }

        questionsRecyclerView = findViewById(R.id.questions_list_view)
        questionsRecyclerView.layoutManager = LinearLayoutManager(getContext())
        questionsListAdapter = QuestionsListAdapter(this, viewMvcFactory)
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
}