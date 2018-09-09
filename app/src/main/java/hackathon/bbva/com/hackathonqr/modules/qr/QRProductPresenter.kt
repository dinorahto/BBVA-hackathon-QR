package hackathon.bbva.com.hackathonqr.modules.qr

import hackathon.bbva.com.qrsdk.transactions.TransactionsRepository

/**
 * Created by Dinorah Tovar on 9/9/18
 */
class QRProductPresenter
/**
 * Constructor
 * @param view
 */
(private val transactionsRepository: TransactionsRepository, private val view: QRProductView) {

    fun getProductsForTransactions () {
        transactionsRepository.getProductsAvailable().subscribe({
            view.setProductsAvailables(it)
        }, {})
    }
}