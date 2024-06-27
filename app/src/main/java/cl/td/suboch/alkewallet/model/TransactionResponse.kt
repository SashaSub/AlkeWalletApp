package cl.td.suboch.alkewallet.model

data class TransactionResponse(
    val previousPage: Any?,
    val nextPage: Any?,
    val data: List<Transaction>?,
    val error: String?,
    val status: String?
)
