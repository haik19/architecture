package com.example.myapplication.screens.questionslist

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(@SerializedName("items") val list: List<Question>)