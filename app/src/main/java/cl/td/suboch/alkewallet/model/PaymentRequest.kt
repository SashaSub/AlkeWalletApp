package cl.td.suboch.alkewallet.model

data class PaymentRequest(
    val type: String,
    val concept: String,
    val amount: Double
)
