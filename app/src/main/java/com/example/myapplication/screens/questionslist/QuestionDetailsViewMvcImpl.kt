package com.example.myapplication.screens.questionslist

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.screens.model.QuestionDetails

class QuestionDetailsViewMvcImpl(layoutInflater: LayoutInflater) : QuestionDetailsViewMvc {

	override val rootView: View =
		layoutInflater.inflate(R.layout.question_details_layout, null, false)

	private val progressBar: ProgressBar = rootView.findViewById(R.id.progress_bar)
	private val titleView: TextView = rootView.findViewById(R.id.question_title)
	private val questionsDescriptionView: TextView = rootView.findViewById(R.id.question_details)

	override fun bindQuestionDetails(questionDetails: QuestionDetails) {
		questionDetails.apply {
			titleView.text = title
			questionsDescriptionView.text = Html.fromHtml(body, Html.FROM_HTML_MODE_COMPACT)

		}
	}

	override fun showProgress() {
		progressBar.visibility = View.VISIBLE
	}

	override fun hideProgress() {
		progressBar.visibility = View.GONE
	}
}