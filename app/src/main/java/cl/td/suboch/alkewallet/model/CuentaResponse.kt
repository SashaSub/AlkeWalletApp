package cl.td.suboch.alkewallet.model

data class CuentaResponse(
    val id: Int,
    val creationDate: String?,
    val money: Double?,
    val isBlocked: Boolean?,
    val userId: Int?,
    val error: Int?,
    val status: Int?
)
