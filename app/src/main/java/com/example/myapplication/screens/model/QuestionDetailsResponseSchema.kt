package com.example.myapplication.screens.model

import com.google.gson.annotations.SerializedName

data class QuestionDetailsResponse (@SerializedName("items") val list: List<QuestionSchema>)