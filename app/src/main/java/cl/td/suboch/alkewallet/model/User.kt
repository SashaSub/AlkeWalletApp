package cl.td.suboch.alkewallet.model

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val roleId: Long = 2,
    val points: Long = 0
)
