package hackathon.bbva.com.hackathonqr.modules.accounts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.MyApplication
import hackathon.bbva.com.qrsdk.transactions.TransactionsRepository
import hackathon.bbva.com.qrsdk.transactions.domain.TransactionsResponseViewModel
import kotlinx.android.synthetic.main.activity_account.*
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/8/18
 */

class AccountActivity: AppCompatActivity(), AccountView {

    @Inject internal lateinit var transactionRepository: TransactionsRepository
    private var presenter: AccountPresenter? = null
    private var adapter: MovementAdapter? = null

    /**
     * Starting Activity from Login Activity
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.mAppComponent?.inject(this)
        setContentView(R.layout.activity_account)
        presenter = AccountPresenter (transactionRepository, this)
        setView()
        presenter?.getTransactions()
    }

    /**
     * Setting view for recycler elements
     */
    private fun setView () {
        adapter = MovementAdapter(object: OnClickMovementListener {
            override fun onMovementSelected() {
                val movementDetail = MovementDetail()
                supportFragmentManager?.beginTransaction()
                        ?.add(R.id.movement_container, movementDetail, MovementDetail::class.java.simpleName)
                        ?.addToBackStack(null)
                        ?.commit()
            }
        })
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        account_movements_recycler_view.layoutManager = layoutManager
        account_movements_recycler_view.adapter = adapter
    }

    override fun setTransactions(transactions: List<TransactionsResponseViewModel>) {
        adapter?.transactions = transactions
        adapter?.notifyDataSetChanged()
    }
}