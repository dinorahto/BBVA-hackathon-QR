package hackathon.bbva.com.qrsdk.qr.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class QRLayoutdOP{
        @field:SerializedName("alias")
        @field:Expose
        var alias: String? = null
        @field:SerializedName("cl")
        @field:Expose
        var cl: String? = null
        @field:SerializedName("type")
        @field:Expose
        var type: String? = null
        @field:SerializedName("refn")
        @field:Expose
        var refn: String? = null
        @field:SerializedName("refa")
        @field:Expose
        var refa: String? = null
        @field:SerializedName("amount")
        @field:Expose
        var amount: Double? = 0.0
        @field:SerializedName("bank")
        @field:Expose
        var bank: String? = null
        @field:SerializedName("country")
        @field:Expose
        var country: String? = null
        @field:SerializedName("currency")
        @field:Expose
        var currency: String? = null
}