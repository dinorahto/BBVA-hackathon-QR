package hackathon.bbva.com.hackathonqr.modules.qr

import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel

/**
 * Created by Dinorah Tovar on 9/9/18
 */
interface QRProductView {
    fun setProductsAvailables (products: List<ProductsResponseViewModel>)
}