package hackathon.bbva.com.hackathonqr.modules.qr

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.holder.ProductHolder
import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class QRAdapterProducts: RecyclerView.Adapter<ProductHolder> () {

    var products: List<ProductsResponseViewModel>? = null
    var lastHeaderAvailable: String? = null
    var productsSelected = ArrayList<ProductsResponseViewModel>()

    /**
     * Inflate layout to fill views in holder
     * @param parent ViewGroup
     * @param viewType Int
     * @return BillAmountHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_products_qr, parent, false))
    }

    /**
     * Set values in holder with listener
     * @param holder BillAmountHolder
     * @param position Int
     */
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.setProducts(products?.get(position))
        if (position == 0) {
            lastHeaderAvailable = products?.get(position)?.nombre?.get(0).toString()
            holder.setHeader(products?.get(position)?.nombre?.get(0).toString())
        } else {
            if (lastHeaderAvailable.equals(products?.get(position)?.nombre?.get(0).toString()))
                holder.hideHeader()
            else
                holder.setHeader(products?.get(position)?.nombre?.get(0).toString())
        }
        holder.itemView.setOnClickListener {
            holder.setCheckedRadio(true)
            notifyDataSetChanged()
            productsSelected.add(products?.get(position)!!)
        }
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