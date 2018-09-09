package hackathon.bbva.com.hackathonqr.modules.login

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class AddAccountPresenter
/**
 * Constructor
 * @param view
 */
(private val view: AddAccountView) {

    fun verifyData (phone: String?, clabe: String?) {
        if (phone.isNullOrEmpty())
            return

        if (clabe.isNullOrEmpty())
            return

        view.createAccountNextStep(phone, clabe)
    }
}