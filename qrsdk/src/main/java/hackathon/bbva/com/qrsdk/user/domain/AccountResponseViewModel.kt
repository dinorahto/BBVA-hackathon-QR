package hackathon.bbva.com.qrsdk.user.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class AccountResponseViewModel (@SerializedName("tarjeta")
                                @Expose
                                var tarjeta: String? = null,
                                @field:SerializedName("clabe")
                                @field:Expose
                                var clabe: String? = null,
                                @field:SerializedName("numeroCuenta")
                                @field:Expose
                                var numeroCuenta: String? = null)