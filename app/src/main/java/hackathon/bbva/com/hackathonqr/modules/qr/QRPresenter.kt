package hackathon.bbva.com.hackathonqr.modules.qr

/**
 * Created by Dinorah Tovar on 9/8/18
 * Presenter for login for the QR express and the normal one
 */
class QRPresenter
/**
 * Constructor
 * @param view
 */
(private val view: QRView) {

    /**
     * Verify the amount of the view available
     */
    fun verifyAmount (amount: Double?) {
        if (amount == null) {
            view.onError()
            return
        }

        if (amount < 0) {
            view.onError ()
            return
        }

        view.nextStep(amount)
    }

}