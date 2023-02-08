package task.internship.app.presentation.helper

data class TypeDataClass(
    val isTypeMessage: Boolean,
    val username: String,
    val message: String? = null,
    val hasJoined: Boolean? = null,
    val totalParticipants: Int? = null
)
