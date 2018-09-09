package hackathon.bbva.com.hackathonqr.modules.qr

import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel

/**
 * Created by Dinorah Tovar on 9/9/18
 */
interface QRConfirmationView {
    fun goToHome()
    fun settingView (name: String?)
    fun settingAdapter (products: List<ProductsResponseViewModel>)
    fun settingAmounts (subtotal: Double, iva: Double, total: Double, product: Int)
}