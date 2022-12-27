package com.littlesoul.model.home


data class HomeCategoryData(
    var category: List<Category> = listOf(),
    var user: User = User()
)