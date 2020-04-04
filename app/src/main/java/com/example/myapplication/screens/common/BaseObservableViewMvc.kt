package com.example.myapplication.screens.common

import java.util.*


abstract class BaseObservableViewMvc<ListenerType> : BaseViewMvc(),
	ObservableViewMvc<ListenerType> {

	val listeners: MutableSet<ListenerType> = hashSetOf()

	override fun registersListener(listener: ListenerType) {
		listeners.add(listener)
	}

	override fun unregisterListener(listener: ListenerType) {
		listeners.remove(listener)
	}
}