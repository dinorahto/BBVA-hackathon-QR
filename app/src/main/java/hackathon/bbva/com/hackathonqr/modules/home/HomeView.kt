package hackathon.bbva.com.hackathonqr.modules.home

import hackathon.bbva.com.qrsdk.transactions.domain.TransactionsResponseViewModel

/**
 * Created by Dinorah Tovar on 9/8/18
 */
interface HomeView {
    fun setNameBusiness(razonSocial: String?, account: String?)
    fun setViewTransaction (transaction: TransactionsResponseViewModel)
}