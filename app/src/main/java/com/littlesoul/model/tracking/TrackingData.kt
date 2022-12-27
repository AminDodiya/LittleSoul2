package com.littlesoul.model.tracking


data class TrackingData(
    var category: List<Category> = listOf(),
    var goal: List<Goal> = listOf(),
    var tracking: List<Tracking> = listOf()
)