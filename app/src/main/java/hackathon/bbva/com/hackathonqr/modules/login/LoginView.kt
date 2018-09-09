package hackathon.bbva.com.hackathonqr.modules.login

/**
 * Created by Dinorah Tovar on 9/8/18
 * LoginView Interface
 */
interface LoginView {
    fun setLogin ()
    fun setAccount ()
    fun createAccountFirstStep (telefono: String?)
}