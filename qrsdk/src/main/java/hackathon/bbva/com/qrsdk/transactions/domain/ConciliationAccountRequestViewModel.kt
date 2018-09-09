package hackathon.bbva.com.qrsdk.transactions.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dinorah Tovar on 9/9/18
 */
class ConciliationAccountRequestViewModel (@SerializedName("clabe")
                                           @Expose
                                           val clabe: String? = null)