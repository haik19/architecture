package com.example.myapplication.screens.common

import androidx.fragment.app.FragmentActivity
import com.example.myapplication.screens.MyApplication
import com.example.myapplication.screens.common.dependancyinjection.ControllerCompostionRoot

open class BaseActivity : FragmentActivity(){

	private var controllerCompostionRoot : ControllerCompostionRoot? = null

	protected fun compositionRoot(): ControllerCompostionRoot {
		if (controllerCompostionRoot == null) {
			controllerCompostionRoot =
				ControllerCompostionRoot((application as MyApplication).compositionRoot, this)
		}
		return controllerCompostionRoot!!
	}
}
