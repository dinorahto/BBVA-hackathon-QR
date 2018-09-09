package hackathon.bbva.com.hackathonqr.modules.qr

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.MyApplication
import hackathon.bbva.com.hackathonqr.modules.login.AddAccountFragment
import hackathon.bbva.com.hackathonqr.modules.login.AddBusinessFragment
import hackathon.bbva.com.qrsdk.transactions.TransactionsRepository
import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel
import kotlinx.android.synthetic.main.fragment_qr_products.*
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class QRProductFragment: Fragment(), QRProductView {

    @Inject internal lateinit var transactionRepository: TransactionsRepository
    private var adapter: QRAdapterProducts? = null
    private var presenter: QRProductPresenter? = null

    /**
     * onCreate
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.mAppComponent?.inject(this)
        activity?.title = getString(R.string.title_products_qr)
        presenter = QRProductPresenter(transactionRepository, this)
    }

    /**
     * Creates the view, called from ActivityFragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_qr_products, container, false)
    }

    /**
     * Set the view after creation
     * @param savedInstanceState
     * @param view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setView()
        presenter?.getProductsForTransactions()
    }

    /**
     * Set View for products
     */
    private fun setView () {
        adapter = QRAdapterProducts()
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        qr_products_recycler_view.layoutManager = layoutManager
        qr_products_recycler_view.adapter = adapter

        qr_products_generate.setOnClickListener {
            val products = adapter?.productsSelected
            val qrConfirmation = QRConfirmation.newInstance(products)
            fragmentManager?.beginTransaction()
                    ?.add(R.id.container_qr, qrConfirmation, QRConfirmation::class.java.simpleName)
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }

    override fun setProductsAvailables(products: List<ProductsResponseViewModel>) {
        adapter?.products = products
        adapter?.notifyDataSetChanged()
    }
}