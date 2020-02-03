package com.example.myapplication.screens.questionslist

import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsListActivity : BaseActivity(), IQuestionsListViewMvcI.Listener {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.stackexchange.com/")
        .build()
    private lateinit var questionsMvcViewImpl: IQuestionsListViewMvcI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionsMvcViewImpl = QuestionsListViewMvcImpl(layoutInflater)
        questionsMvcViewImpl.registersListener(this)
        setContentView(questionsMvcViewImpl.rootView)

        retrofit.create<QuestionsService>(QuestionsService::class.java).getQuestions("stackoverflow")
            .enqueue(object : Callback<QuestionsResponse> {
                override fun onResponse(
                    call: Call<QuestionsResponse>,
                    response: Response<QuestionsResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            bindQuestionsList(it)
                        }
                    }
                }

                override fun onFailure(call: Call<QuestionsResponse>, t: Throwable) {
                    //no need yet
                }
            })
    }

    fun bindQuestionsList(questionsResponse: QuestionsResponse) {
        val list = mutableListOf<Question>()
        for (q in questionsResponse.list) {
            //for test
        }
        questionsMvcViewImpl.bindQuestions(questionsResponse.list)
    }

    override fun onQuestionClicked(question: Question) {
        Toast.makeText(this, question.title, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        questionsMvcViewImpl.unregisterListener(this)
    }
}
