package hackathon.bbva.com.hackathonqr.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel
import kotlinx.android.synthetic.main.layout_product_sale.view.*

/**
 * Created by Dinorah Tovar on 9/8/18
 * Holder for elements
*/

class ProductSaleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Set view for products on the sale
    */
    fun setInfo (productSaleHolder: ProductsResponseViewModel?) {
        itemView.product_sale_name.text = productSaleHolder?.nombre
        itemView.product_sale_amount.text = productSaleHolder?.precio.toString()
    }
}