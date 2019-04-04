package net.alexandroid.teamco.model

data class Event(
    val timestamp: String, //Timestamp(Date())
    val title: String,
    val description: String,
    val eventDate: String,
    val location: String,
    val logoUrl: String,
    val userTypes: String,
    val membersLink: String,
    val mentorsCode: String,
    val teamSizeMin: Int,
    val teamSizeMax: Int,
    val isUserCanCreate: Boolean,
    val isUserCanJoin: Boolean,
    val isMentorsEnabled: Boolean,
    val isUserExperienceEnabled: Boolean,
    val teams: List<String>,
    val users: List<String>
)
