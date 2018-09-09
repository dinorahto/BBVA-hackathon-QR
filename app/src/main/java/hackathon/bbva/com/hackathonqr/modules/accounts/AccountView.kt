package hackathon.bbva.com.hackathonqr.modules.accounts

import hackathon.bbva.com.qrsdk.transactions.domain.TransactionsResponseViewModel

/**
 * Created by Dinorah Tovar on 9/8/18
 */
interface AccountView {
    fun setTransactions(transactions: List<TransactionsResponseViewModel> )
}