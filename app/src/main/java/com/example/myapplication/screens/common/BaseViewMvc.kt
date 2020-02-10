package com.example.myapplication.screens.common

import android.content.Context
import android.view.View

abstract class BaseViewMvc : ViewMvc {

	override lateinit var rootView: View
	protected set

	protected fun <T : View> findViewById(id: Int): T = rootView.findViewById(id)

	protected fun getContext(): Context = rootView.context

}
