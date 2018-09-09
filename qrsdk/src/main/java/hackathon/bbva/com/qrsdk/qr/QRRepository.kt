package hackathon.bbva.com.qrsdk.qr

import hackathon.bbva.com.qrsdk.qr.domain.QRLayout
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/8/18
 */

class QRRepository
@Inject
constructor(private val qrRemoteStorage: QRRemoteStorage) {

    fun getStringFromJson(objectToString: QRLayout): String? {
        return qrRemoteStorage.getStringFromJson(objectToString)
    }

}