package com.example.myapplication.screens.model

import com.google.gson.annotations.SerializedName

data class QuestionsListResponse(@SerializedName("items") val list: List<QuestionSchema>)