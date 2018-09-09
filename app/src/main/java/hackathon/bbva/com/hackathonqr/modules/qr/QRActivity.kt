package hackathon.bbva.com.hackathonqr.modules.qr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.modules.login.AddAccountFragment
import hackathon.bbva.com.hackathonqr.modules.login.AddBusinessFragment
import hackathon.bbva.com.hackathonqr.util.CloseKeyboardUtil
import hackathon.bbva.com.qrsdk.DaggerInjectorQR
import kotlinx.android.synthetic.main.activity_qr.*

/**
 * Created by Dinorah Tovar on 9/8/18
 * QrActivity get the values for the QR express or the QR over products
 */

class QRActivity: AppCompatActivity(), QRView {

    private var presenter: QRPresenter? = null

    /**
     * Starting Activity from QrActivity
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)
        title = getString(R.string.title_fast_qr)
        presenter = QRPresenter (this)
        setListener()
    }

    /**
     * Set on back pressed result
     */
    override fun onBackPressed() {
        finish()
        setResult(RESULT_OK)
    }

    /**
     * Setting listener for the views
     */
    private fun setListener () {
        qr_generate.setOnClickListener { presenter?.verifyAmount(qr_quantity.text.toString().toDoubleOrNull())}
        qr_generate_product.setOnClickListener {
            qr_generate.visibility = View.GONE
            val qrProductFragment = QRProductFragment()
            supportFragmentManager?.beginTransaction()
                    ?.add(R.id.container_qr, qrProductFragment, QRProductFragment::class.java.simpleName)
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }

    /**
     * Setting on error view
     */
    override fun onError() {
        qr_quantity.error = getString(R.string.error_amount)
    }

    /**
     * Setting the next step for the view without products
     */
    override fun nextStep(total: Double) {
        CloseKeyboardUtil.closeKeyboard(currentFocus, DaggerInjectorQR.context)
        qr_generate.visibility = View.GONE
        val qrConfirmation = QRConfirmation()
        val data = Bundle()
        data.putDouble(QRConfirmation.TOTAL, total)
        qrConfirmation.arguments = data
        supportFragmentManager?.beginTransaction()
                ?.add(R.id.container_qr, qrConfirmation, QRConfirmation::class.java.simpleName)
                ?.addToBackStack(null)
                ?.commit()
    }


}