package com.example.myapplication.screens

import android.app.Application
import com.example.myapplication.screens.common.dependancyinjection.CompositionRoot

class MyApplication : Application() {

	lateinit var compositionRoot: CompositionRoot

	override fun onCreate() {
		super.onCreate()
		compositionRoot = CompositionRoot()
	}
}