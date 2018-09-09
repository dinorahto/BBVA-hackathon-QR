package hackathon.bbva.com.hackathonqr.modules.login

import hackathon.bbva.com.qrsdk.user.LoginRepository

/**
 * Created by Dinorah Tovar on 9/8/18
 */
class AddBusinessPresenter
/**
 * Constructor
 * @param view
 */
(private val loginRepository: LoginRepository, private val view: AddBusinessView) {

    /**
     * Verify data
     */
    fun verifyData (phone: String?, clabe: String?, name: String?, password: String?) {
        if (phone.isNullOrEmpty())
            return

        if (clabe.isNullOrEmpty())
            return

        if (name.isNullOrEmpty())
            return

        if (password.isNullOrEmpty())
            return

        goToCreateAccount(phone, clabe, name, password)
    }

    /**
     * Create Account
     */
    private fun goToCreateAccount (phone: String?, clabe: String?, name: String?, password: String?) {
        loginRepository.createAccount(name, "", password, phone, clabe).subscribe({
            view.goToHome()
        }, {})
    }

}