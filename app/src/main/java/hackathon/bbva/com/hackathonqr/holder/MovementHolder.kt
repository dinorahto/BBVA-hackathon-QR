package hackathon.bbva.com.hackathonqr.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.util.MoneyUtils
import hackathon.bbva.com.qrsdk.transactions.domain.TransactionsResponseViewModel
import kotlinx.android.synthetic.main.layout_movements_holder.view.*

/**
 * Created by Dinorah Tovar on 9/8/18
 */

class MovementHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setView (transaction: TransactionsResponseViewModel?) {
        val cuenta = itemView.context.getString(R.string.home_clabe) + transaction?.cuenta?.clabe?.substring(transaction.cuenta?.clabe?.length!! - 4)
        itemView.movement_name.text = cuenta
        itemView.movement_id.text = transaction?.id
        itemView.movement_amount.text = MoneyUtils.setCurrency(transaction?.amount!!)
    }
}