package hackathon.bbva.com.hackathonqr.modules.accounts

import hackathon.bbva.com.qrsdk.transactions.TransactionsRepository

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class AccountPresenter
/**
 * Constructor
 * @param view
 */
(private val transactionsRepository: TransactionsRepository, private val view: AccountView) {

    /**
     * Searches for a stored user that has logged in this phone
     */
    fun getTransactions () {
        transactionsRepository.getTransactionsAvailable().subscribe({
            view.setTransactions(it)
        }, {})
    }

}