package com.example.myapplication.screens.common.dependancyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.example.myapplication.screens.common.ViewMvcFactory

class ControllerCompostionRoot(val compostionRoot: CompositionRoot, val activity: Activity) {

	fun getStackOverFlowApi() = compostionRoot.getStackOverFlowApi()

	fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

	fun getViewMvcFactory() = ViewMvcFactory(getLayoutInflater())
}