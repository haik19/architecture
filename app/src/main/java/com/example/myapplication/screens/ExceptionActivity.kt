package com.example.myapplication.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.os.PersistableBundle
import android.os.Process
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

const val EXCEPTION_MESSAGE_KEY = "exception_message_key"

class ExceptionActivity : FragmentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val exceptionMessage = intent.getStringExtra(EXCEPTION_MESSAGE_KEY)
		setContentView(TextView(this).apply {
			text = exceptionMessage
			setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
			textAlignment = View.TEXT_ALIGNMENT_CENTER
		})
	}


	companion object {
		fun startActivity(context: Context, message: String) {
			val i = Intent(context, ExceptionActivity::class.java)
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
			i.putExtra(EXCEPTION_MESSAGE_KEY, message)
			context.startActivity(i)
			Process.killProcess(Process.myPid())
		}
	}
}