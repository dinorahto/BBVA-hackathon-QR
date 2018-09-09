package hackathon.bbva.com.qrsdk.qr.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class QRLayout {
    @SerializedName("ot")
    @Expose
    var ot: String? = null
    @SerializedName("dOp")
    @Expose
    var dOp: List<QRLayoutdOP>? = null
}