package hackathon.bbva.com.qrsdk.user.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class LoginRequestViewModel (@field:SerializedName("razonSocial")
                             @field:Expose
                             var razonSocial: String? = null,
                             @field:SerializedName("nombre")
                             @field:Expose
                             var nombre: String? = null,
                             @field:SerializedName("pwd")
                             @field:Expose
                             var pwd: String? = null,
                             @field:SerializedName("telefono")
                             @field:Expose
                             var telefono: String? = null,
                             @field:SerializedName("cuentas")
                             @field:Expose
                             var cuentas: List<AccountRequestViewModel>? = null)