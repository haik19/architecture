package com.example.myapplication.screens.common

import com.example.myapplication.screens.common.Question
import com.google.gson.annotations.SerializedName

data class QuestionsResponse(@SerializedName("items") val list: List<Question>)