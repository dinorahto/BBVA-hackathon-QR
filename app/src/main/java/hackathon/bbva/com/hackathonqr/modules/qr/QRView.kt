package hackathon.bbva.com.hackathonqr.modules.qr

/**
 * Created by Dinorah Tovar on 9/8/18
 */
interface QRView {
    fun onError ()
    fun nextStep (total: Double)
}