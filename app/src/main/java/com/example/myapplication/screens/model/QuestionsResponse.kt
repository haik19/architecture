package com.example.myapplication.screens.model

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(@SerializedName("items") val list: List<Question>)