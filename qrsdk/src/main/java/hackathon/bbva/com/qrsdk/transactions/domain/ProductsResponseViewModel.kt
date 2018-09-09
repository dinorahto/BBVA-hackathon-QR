package hackathon.bbva.com.qrsdk.transactions.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dinorah Tovar on 9/9/18
 */
class ProductsResponseViewModel (@field:SerializedName("id")
                                 @field:Expose
                                 var id: String? = null,
                                 @field:SerializedName("nombre")
                                 @field:Expose
                                 var nombre: String? = null,
                                 @field:SerializedName("precio")
                                 @field:Expose
                                 var precio: Double? = null,
                                 @field:SerializedName("urlImagen")
                                 @field:Expose
                                 var urlImagen: String? = null,
                                 @field:SerializedName("upc")
                                 @field:Expose
                                 var upc: String? = null,
                                 @field:SerializedName("idUser")
                                 @field:Expose
                                 var idUser: String? = null)