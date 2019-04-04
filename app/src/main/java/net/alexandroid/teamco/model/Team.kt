package net.alexandroid.teamco.model

data class Team(
    val timestamp: String,
    val title: String,
    val description: String,
    val logoUrl: String,
    val userList: List<String>
)