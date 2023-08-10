package com.bluetiger.foodbrocompose.database.user

enum class ActivityDataValueType(
    val shortTerm: String,
    val description: String,
    val minPalFactor: Float,
    val maxPalFactor: Float,
    val meanPalFactor: Float,
    val examples: String
) {
    SLEEP(
        "Sleep",
        "Sleeping",
        0.95f,
        0.95f,
        0.95f,
        "-"),
    LIGHTLY_ACTIVE(
        "Lightly Active",
        "Only Sitting or lying",
        1.2f,
        1.2f,
        1.2f,
        "Reading, watching Tv"),
    MODERATELY_ACTIVE(
        "Moderate Active",
        "Nearly Sitting, little activity",
        1.4f,
        1.5f,
        1.45f,
        "Office activity"),
    ACTIVE(
        "Active",
        "Most Time Sitting, some staying Situations",
        1.6f,
        1.7f,
        1.65f,
        "Students, Driver, Laboratory"
    ),
    VERY_ACTIVE(
        "Very active",
        "Most time staying, walking work",
        1.8f,
        1.9f,
        1.85f,
        "Sellers, Waiters, Craftstmen, HouseWife/Man"
    ),
    HARD_ACTIVE(
        "Hard Active",
        "Hard exhaustive Work",
        2.0f,
        2.4f,
        2.2f,
        "Miners, Farmers, Forest workers, High performance sportler"
    )
}