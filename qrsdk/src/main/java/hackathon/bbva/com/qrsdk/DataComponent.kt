package hackathon.bbva.com.qrsdk

import hackathon.bbva.com.qrsdk.qr.QRRemoteStorage
import hackathon.bbva.com.qrsdk.transactions.TransactionsRemoteStorage
import hackathon.bbva.com.qrsdk.user.LoginLocalStorage
import hackathon.bbva.com.qrsdk.user.LoginRemoteStorage

/**
 * Created by Dinorah Tovar on 2/14/18.
 * Interface for injections
 */

interface DataComponent {
    fun inject(loginRemoteStorage: LoginRemoteStorage)
    fun inject(loginLocalStorage: LoginLocalStorage)
    fun inject(qrRemoteStorage: QRRemoteStorage)
    fun inject(transactionsRemoteStorage: TransactionsRemoteStorage)
}