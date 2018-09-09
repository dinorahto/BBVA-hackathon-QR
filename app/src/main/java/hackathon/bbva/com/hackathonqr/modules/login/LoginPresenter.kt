package hackathon.bbva.com.hackathonqr.modules.login

import hackathon.bbva.com.qrsdk.user.LoginRepository

/**
 * Created by Dinorah Tovar on 9/8/18
 * Splash Presenter where all Splash Logic is handled
 */

class LoginPresenter
/**
 * Constructor
 * @param loginRepository
 * @param view
 */
(private val loginRepository: LoginRepository, private val view: LoginView) {

    /**
     * Searches for a stored user that has logged in this phone
     */
    fun searchUser() {
        val user = loginRepository.currentUser()
        if (user != null) {
            view.setLogin()
        } else {
            view.setAccount()
        }
    }

    fun validaUser (telefono: String?) {
        if (telefono.isNullOrEmpty())
            return

        loginRepository.validateUserAvailable(telefono).subscribe({
            view.setLogin()
        }, {
            view.createAccountFirstStep(telefono)
        })
    }



}