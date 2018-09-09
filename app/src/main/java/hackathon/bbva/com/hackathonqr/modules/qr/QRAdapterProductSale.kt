package hackathon.bbva.com.hackathonqr.modules.qr

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.holder.ProductSaleHolder
import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class QRAdapterProductSale: RecyclerView.Adapter<ProductSaleHolder> () {

    var products: List<ProductsResponseViewModel>? = null

    /**
     * Inflate layout to fill views in holder
     * @param parent ViewGroup
     * @param viewType Int
     * @return BillAmountHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSaleHolder {
        return ProductSaleHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_product_sale, parent, false))
    }

    /**
     * Set values in holder with listener
     * @param holder BillAmountHolder
     * @param position Int
     */
    override fun onBindViewHolder(holder: ProductSaleHolder, position: Int) {
        holder.setInfo(products?.get(position))
    }

    /**
     * Get Item count
     * @return Int
     */
    override fun getItemCount(): Int {
        return if (products != null)
            products?.size!!
        else
            0
    }

}