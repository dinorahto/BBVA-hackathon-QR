package hackathon.bbva.com.qrsdk.user.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class LoginResponseViewModel (@field:SerializedName("id")
                              @field:Expose
                              val id: String? = null,
                              @field:SerializedName("razonSocial")
                              @field:Expose
                              val razonSocial: String? = null,
                              @field:SerializedName("nombre")
                              @field:Expose
                              val nombre: String? = null,
                              @field:SerializedName("pwd")
                              @field:Expose
                              val pwd: String? = null,
                              @field:SerializedName("telefono")
                              @field:Expose
                              val telefono: String? = null,
                              @field:SerializedName("cuentas")
                              @field:Expose
                              val cuentas: List<AccountResponseViewModel>? = null,
                              @field:SerializedName("role")
                              @field:Expose
                              val role: String? = null)