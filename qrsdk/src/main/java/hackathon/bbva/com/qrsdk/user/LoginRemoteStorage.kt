package hackathon.bbva.com.qrsdk.user

import hackathon.bbva.com.qrsdk.DaggerInjectorQR
import hackathon.bbva.com.qrsdk.user.domain.AccountRequestViewModel
import hackathon.bbva.com.qrsdk.user.domain.LoginRequestViewModel
import hackathon.bbva.com.qrsdk.user.domain.LoginResponseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/7/18
 * Remote storage for the Login repository
 */
class LoginRemoteStorage {

    @Inject internal lateinit var retrofit: Retrofit
    @Inject internal lateinit var loginLocalStorage: LoginLocalStorage

    /**
     * Injects Dagger on Construction
     */
    init {
        DaggerInjectorQR.dataComponent?.inject(this)
    }

    /**
     * Validate the user is available for the Login or the create account
     * @param telefono: String
     */
    internal fun validateUserAvailable (telefono: String?): Observable<LoginResponseViewModel> {
        return retrofit.create(LoginService::class.java)
                .userAvailable(telefono)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Create account
     * @param razonSocial
     * @param nombre
     * @param password
     * @param telefono
     * @param clabe
     */
    internal fun createAccount (razonSocial: String?, nombre: String?, password: String?, telefono: String?, clabe: String?): Observable<LoginResponseViewModel> {
        val accountRequestViewModel = AccountRequestViewModel(clabe)
        val listAccount = mutableListOf<AccountRequestViewModel>()
        listAccount.add(accountRequestViewModel)
        val loginResponseViewModel = LoginRequestViewModel (razonSocial, nombre, password, telefono, listAccount)
        return retrofit.create(LoginService::class.java)
                .createAccount(loginResponseViewModel)
                .doOnNext {
                    loginLocalStorage.saveLocalUserDB(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Interfaces to the web services
     */
    private interface LoginService {
        @GET("api/user/{phone}")
        fun userAvailable(@Path("phone") phone: String?): Observable<LoginResponseViewModel>

        @POST("api/user")
        fun createAccount(@Body loginRequestViewModel: LoginRequestViewModel): Observable<LoginResponseViewModel>
    }

}