package com.example.myapplication.screens

import android.app.Application
import com.example.myapplication.screens.common.dependancyinjection.CompositionRoot
import java.lang.StringBuilder

class MyApplication : Application() {
	lateinit var compositionRoot: CompositionRoot
	override fun onCreate() {
		super.onCreate()
		compositionRoot = CompositionRoot()
		Thread.setDefaultUncaughtExceptionHandler { t, s ->
			val stackTrace = s.stackTrace
			val stringBuilder = StringBuilder()
			stringBuilder.append(s.message).append("\n").append("\n").append(stackTrace.joinToString(separator = "\n"))
			ExceptionActivity.startActivity(this, stringBuilder.toString() ?: "")
		}
	}
}