package hackathon.bbva.com.qrsdk.user

import hackathon.bbva.com.qrsdk.user.database.UserDB
import hackathon.bbva.com.qrsdk.user.domain.LoginResponseViewModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/7/18
 * Repository for the Login
 */

class LoginRepository
@Inject
constructor(private val loginRemoteStorage: LoginRemoteStorage, private val loginLocalStorage: LoginLocalStorage) {

    fun currentUser(): UserDB? {
        return loginLocalStorage.currentUser()
    }

    fun validateUserAvailable (telefono: String?): Observable<LoginResponseViewModel> {
        return loginRemoteStorage.validateUserAvailable(telefono)
    }

    fun createAccount(razonSocial: String?, nombre: String?, password: String?, telefono: String?, clabe: String?): Observable<LoginResponseViewModel> {
        return loginRemoteStorage.createAccount(razonSocial, nombre, password, telefono, clabe)
    }
}