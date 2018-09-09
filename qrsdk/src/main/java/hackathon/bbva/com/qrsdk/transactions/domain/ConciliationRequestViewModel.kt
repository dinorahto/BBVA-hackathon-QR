package hackathon.bbva.com.qrsdk.transactions.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dinorah Tovar on 9/9/18
 */
class ConciliationRequestViewModel (@SerializedName("alfanumerica")
                                    @Expose
                                    val alfanumerica: String? = null,
                                    @SerializedName("amount")
                                    @Expose
                                    val amount: Double? = null,
                                    @SerializedName("cuenta")
                                    @Expose
                                    val cuenta: ConciliationAccountRequestViewModel,
                                    @SerializedName("email")
                                    @Expose
                                    val email: String? = null,
                                    @SerializedName("idDevice")
                                    @Expose
                                    val idDevice: String? = null)