package cl.td.suboch.alkewallet.model

data class CuentaRequest(
    val creationDate: String,
    val money: Double = 200000.0,
    val isBlocked: Boolean = false,
    val userId: Int
)
