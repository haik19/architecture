package com.example.myapplication.screens.common.dependancyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.example.myapplication.screens.common.ViewMvcFactory
import com.example.myapplication.screens.questionslist.FetchQuestionsDetailsUseCase
import com.example.myapplication.screens.questionslist.FetchQuestionsListUseCase

class ControllerCompostionRoot(private val compositionRoot: CompositionRoot, private val activity: Activity) {

	private fun getStackOverFlowApi() = compositionRoot.getStackOverFlowApi()

	fun getFetchQuestionDetailsUseCase() = FetchQuestionsDetailsUseCase(getStackOverFlowApi())

	fun getFetchQuestionsListUceCase() = FetchQuestionsListUseCase(getStackOverFlowApi())

	fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

	fun getViewMvcFactory() = ViewMvcFactory(getLayoutInflater())
}