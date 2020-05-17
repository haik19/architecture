package com.example.myapplication.screens.model

import com.google.gson.annotations.SerializedName

data class QuestionSchema(@SerializedName("title") val title: String, @SerializedName("question_id") val id: String)