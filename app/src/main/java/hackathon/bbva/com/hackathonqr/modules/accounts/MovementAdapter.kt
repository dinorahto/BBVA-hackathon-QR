package hackathon.bbva.com.hackathonqr.modules.accounts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.holder.MovementHolder
import hackathon.bbva.com.qrsdk.transactions.domain.TransactionsResponseViewModel

/**
 * Created by Dinorah Tovar on 9/8/18
 * Movement adapter
 */

class MovementAdapter (private var listener: OnClickMovementListener?): RecyclerView.Adapter<MovementHolder> () {

    var transactions: List<TransactionsResponseViewModel>? = null

    /**
     * Inflate layout to fill views in holder
     * @param parent ViewGroup
     * @param viewType Int
     * @return BillAmountHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementHolder {
        return MovementHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_movements_holder, parent, false))
    }

    /**
     * Set values in holder with listener
     * @param holder BillAmountHolder
     * @param position Int
     */
    override fun onBindViewHolder(holder: MovementHolder, position: Int) {
        holder.setView(transactions?.get(position))
        holder.itemView.setOnClickListener {
            listener?.onMovementSelected()
        }
    }

    /**
     * Get Item count
     * @return Int
     */
    override fun getItemCount(): Int {
        if (transactions != null)
            return transactions!!.size
        else
            return 0
    }
}