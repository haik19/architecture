package com.example.myapplication.screens.common


interface ObservableViewMvc<ListenerType> : ViewMvc {
	fun registersListener(listener: ListenerType)
	fun unregisterListener(listener: ListenerType)
}