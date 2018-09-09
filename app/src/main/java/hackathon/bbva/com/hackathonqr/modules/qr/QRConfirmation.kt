package hackathon.bbva.com.hackathonqr.modules.qr

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.MyApplication
import android.view.*
import android.view.MenuInflater
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import hackathon.bbva.com.hackathonqr.util.MoneyUtils
import hackathon.bbva.com.qrsdk.transactions.TransactionsRepository
import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel
import hackathon.bbva.com.qrsdk.user.LoginRepository
import kotlinx.android.synthetic.main.fragment_confirmation.*
import kotlinx.android.synthetic.main.layout_detail_purcharse.*
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/8/18
 */

class QRConfirmation: Fragment(), QRConfirmationView {

    @Inject internal lateinit var loginRepository: LoginRepository
    @Inject internal lateinit var transactionRepository: TransactionsRepository
    private val QRcodeWidth = 500
    private var total: Double? = null
    private var adapter: QRAdapterProductSale? = null
    private var products: List<ProductsResponseViewModel>? = null
    private var presenter: QRConfirmationPresenter? = null

    companion object {
        const val TOTAL = "total"
        /**
         * Instance for when the user is creating an account or changing the pin code
         * @return the fragment
         */
        fun newInstance(products: MutableList<ProductsResponseViewModel>?): QRConfirmation {
            val fragment = QRConfirmation()
            fragment.products = products
            return fragment
        }
    }

    /**
     * onCreate
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.mAppComponent?.inject(this)
        activity?.title = getString(R.string.title_confirmation)
        presenter = QRConfirmationPresenter(loginRepository, transactionRepository,this)
        val bundle = arguments
        if (bundle != null)
            total = bundle.getDouble(TOTAL)
    }

    /**
     * Creates the view, called from ActivityFragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    /**
     * Set the view after creation
     * @param savedInstanceState
     * @param view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        setView()
    }

    /**
     * Menu Options
     * @param menu
     * @return
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.menuInflater?.inflate(R.menu.menu_share, menu)
    }

    /**
     * Options for click in menu
     * @param item
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareTicket()
        }
        return true
    }

    /**
     * Setting view
     */
    private fun setView () {
        if (products == null) {
            purcharse_recycler_view.visibility = View.INVISIBLE
            presenter?.settingDouble(total!!)
        } else {
            adapter = QRAdapterProductSale()
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            purcharse_recycler_view.layoutManager = layoutManager
            purcharse_recycler_view.adapter = adapter
            presenter?.settingProductView(products)
        }
        presenter?.settingView()
        purcharse_final.setOnClickListener { presenter?.confirmation(total!!) }
        generateQR()
    }

    override fun settingAdapter(products: List<ProductsResponseViewModel>) {
        adapter?.products = products
        adapter?.notifyDataSetChanged()
    }

    override fun settingAmounts(subtotal: Double, iva: Double, total: Double, product: Int) {
        this.total = total
        purcharse_subtotal_amount.text = MoneyUtils.setCurrency(subtotal)
        purcharse_iva_amount.text = MoneyUtils.setCurrency(iva)
        purcharse_product_amount.text = product.toString()
        purcharse_total_product_amount.text = MoneyUtils.setCurrency(total)
    }

    override fun settingView(name: String?) {
        purcharse_name.text = name
    }

    override fun goToHome() {
        activity?.finish()
    }
    /**
     * Generate QR
     */
    private fun generateQR () {
        var bitmap: Bitmap? = null
        //val account = "012180027192980453"
        val account = loginRepository?.currentUser()?.clabe
        try {
            bitmap = TextToImageEncode("{\n" +
                    "  \"dOp\": [\n" +
                    "    {\n" +
                    "      \"cl\":" + "\"" + account + "\"" + //\"012180027192980453\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"refn\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"refa\": \"JOSE\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"amount\":" + "\""+ total + "\""+ //\"1.00\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"alias\": \"págame \"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"bank\": \"00012\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"type\": \"cl\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"country\": \"MX\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"currency\": \"MXN\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"ot\": \"0001\"\n" +
                    "}")
            detail_qr_purcharse.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    /**
     * Set Text to image
     * @param value String
     * @return Bitmap
     */
    @Throws(WriterException::class)
    private fun TextToImageEncode(Value: String?): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                    Value, BarcodeFormat.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            )
        } catch (Illegalargumentexception: IllegalArgumentException) {
            return null
        }

        val bitMatrixWidth = bitMatrix.width
        val bitMatrixHeight = bitMatrix.height
        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)
        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth
            for (x in 0 until bitMatrixWidth) {
                pixels[offset + x] = if (bitMatrix.get(x, y))
                    resources.getColor(R.color.colorBlack)
                else
                    resources.getColor(R.color.colorWhite)
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }

    /**
     * Share Ticket View
     */
    private fun shareTicket () {
        confirmation_share.visibility = View.VISIBLE
    }

}