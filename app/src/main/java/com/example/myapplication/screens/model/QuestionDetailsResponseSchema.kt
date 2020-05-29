package com.example.myapplication.screens.model

import com.google.gson.annotations.SerializedName

data class QuestionDetailsResponseSchema (@SerializedName("items") val list: List<QuestionSchema> )