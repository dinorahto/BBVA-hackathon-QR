package hackathon.bbva.com.hackathonqr.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import hackathon.bbva.com.qrsdk.transactions.domain.TransactionsResponseViewModel
import kotlinx.android.synthetic.main.layout_movements_holder.view.*

/**
 * Created by Dinorah Tovar on 9/8/18
 */

class MovementHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setView (transaction: TransactionsResponseViewModel?) {
        itemView.movement_name.text = transaction?.cuenta?.clabe
        itemView.movement_id.text = transaction?.id
        itemView.movement_amount.text = transaction?.amount.toString()
    }
}