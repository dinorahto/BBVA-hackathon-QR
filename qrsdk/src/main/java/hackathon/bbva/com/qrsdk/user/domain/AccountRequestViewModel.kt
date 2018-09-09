package hackathon.bbva.com.qrsdk.user.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class AccountRequestViewModel (@field:SerializedName("clabe")
                               @field:Expose
                               var clabe: String? = null)