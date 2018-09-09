package hackathon.bbva.com.qrsdk.transactions

import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel
import hackathon.bbva.com.qrsdk.transactions.domain.TransactionsResponseViewModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/9/18
 */

class TransactionsRepository
@Inject
constructor(private val transactionsRemoteStorage: TransactionsRemoteStorage) {


    fun getProductsAvailable(): Observable<List<ProductsResponseViewModel>> {
        return transactionsRemoteStorage.getProductsAvailable()
    }

    fun getTransactionsAvailable(): Observable<List<TransactionsResponseViewModel>> {
        return transactionsRemoteStorage.getTransactionsAvailable()
    }

    fun conciliations(alfa: String?, amount: Double, email: String?, idDevice: String?, clabe: String?): Observable<Any> {
        return transactionsRemoteStorage.conciliations(alfa, amount, email, idDevice, clabe)
    }
}