package com.example.myapplication.screens.model

import com.google.gson.annotations.SerializedName

data class QuestionScheam(@SerializedName("title") val title: String, @SerializedName("questionId") val id: String)