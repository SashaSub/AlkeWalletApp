package cl.td.suboch.alkewallet.model

data class AllUsersResponse(
    val first_name: String?,
    val last_name: String?,
    val email: String?,
    val password: String?,
    val roleId : Long?,
    val points : Long?,
    val error : String?,
    val status : Long?
)
