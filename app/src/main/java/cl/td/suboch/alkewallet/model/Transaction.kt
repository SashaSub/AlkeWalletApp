package cl.td.suboch.alkewallet.model

data class Transaction(

    val amount: String,
    val concept: String,
    val date: String,
    val type: String,
    val accountID: Long,
    val userID: Long,
    val toAccountID: Long


)
