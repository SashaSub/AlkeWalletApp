package cl.td.suboch.alkewallet.model

data class Transaction(
    val id: Int,
    val amount: String,
    val concept: String,
    val date: String,
    val type: String,
    val accountID: Int,
    val userID: Int,
    val toAccountID: Int,
    val createdAt: String,
    val updatedAt: String,
    val name: String?,
    val lastName: String?,
    val imgUrl : String?
)
