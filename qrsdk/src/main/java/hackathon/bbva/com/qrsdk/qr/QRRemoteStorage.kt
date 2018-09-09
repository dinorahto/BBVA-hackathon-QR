package hackathon.bbva.com.qrsdk.qr

import hackathon.bbva.com.qrsdk.DaggerInjectorQR
import hackathon.bbva.com.qrsdk.qr.domain.QRLayout
import hackathon.bbva.com.qrsdk.user.LoginLocalStorage
import retrofit2.Retrofit
import javax.inject.Inject
import com.google.gson.Gson



/**
 * Created by Dinorah Tovar on 9/8/18
 * Remote storage for the Login repository
 */
class QRRemoteStorage {

    @Inject internal lateinit var retrofit: Retrofit
    @Inject internal lateinit var loginLocalStorage: LoginLocalStorage

    /**
     * Injects Dagger on Construction
     */
    init {
        DaggerInjectorQR.dataComponent?.inject(this)
    }

    fun getStringFromJson(objectToString: QRLayout): String? {
        val gson = Gson()
        val json = gson.toJson(objectToString)
        return json
    }

}