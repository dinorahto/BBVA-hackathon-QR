package hackathon.bbva.com.hackathonqr.holder

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.util.MoneyUtils
import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel
import kotlinx.android.synthetic.main.layout_products_qr.view.*

/**
 * Created by Dinorah Tovar on 9/8/18
 * Holder for elements
 */

class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Setting view for products
     */
    fun setProducts (product: ProductsResponseViewModel?) {
        Picasso.get().load(product?.urlImagen).into(itemView.products_qr_image)
        itemView.products_qr_description.text = product?.nombre
        itemView.products_qr_price.text = MoneyUtils.setCurrency(product?.precio!!)
    }

    /**
     * Set Header
     * @param header String?
     */
    fun setHeader (header: String?) {
        itemView.products_qr_header.visibility = View.VISIBLE
        itemView.products_qr_header.text = header?.toUpperCase()
    }

    /**
     * Set Header hide
     */
    fun hideHeader () {
        itemView.products_qr_header.visibility = View.GONE
    }

    /**
     * Set view for radio buttons
     * @param checked
     */
    fun setCheckedRadio (checked: Boolean) {
        if (checked)
            itemView.qr_product_selected.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.seleccion))
        else
            itemView.qr_product_selected.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.seleccion_off))
    }

}