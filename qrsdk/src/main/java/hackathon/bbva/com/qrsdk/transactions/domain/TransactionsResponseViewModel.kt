package hackathon.bbva.com.qrsdk.transactions.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import hackathon.bbva.com.qrsdk.user.domain.AccountResponseViewModel
import java.util.*

/**
 * Created by Dinorah Tovar on 9/9/18
 */
class TransactionsResponseViewModel (@SerializedName("id")
                                     @Expose
                                     val id: String? = null,
                                     @SerializedName("alfanumerica")
                                     @Expose
                                     val alfanumerica: String? = null,
                                     @SerializedName("amount")
                                     @Expose
                                     var amount: Double? = 0.0,
                                     @SerializedName("date")
                                     @Expose
                                     var date: Long? = null,
                                     @SerializedName("telefono")
                                     @Expose
                                     val telefono: String? = null,
                                     @SerializedName("cuenta")
                                     @Expose
                                     val cuenta: AccountResponseViewModel? = null,
                                     @SerializedName("idDevice")
                                     @Expose
                                     val idDevice: String?)