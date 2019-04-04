package net.alexandroid.teamco.model

data class User(
    val timestamp: String,
    val name: String,
    val email: String,
    val userType: String,
    val userExperience: Int,
    val eventAdmin: List<String>,
    val eventUser: List<String>,
    val teamAdmin: List<String>,
    val teamUser: List<String>
)