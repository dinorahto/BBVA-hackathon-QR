package hackathon.bbva.com.hackathonqr.util

import java.text.NumberFormat


/**
 * Created by Dinorah Tovar on 9/9/18
 */
object MoneyUtils {

    fun setCurrency (money: Double): String {
        val format = NumberFormat.getCurrencyInstance()
        return format.format(money)
    }
}