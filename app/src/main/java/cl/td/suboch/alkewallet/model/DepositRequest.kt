package cl.td.suboch.alkewallet.model

data class DepositRequest(
    val type : String,
    val concept : String,
    val amonut : Double
)
