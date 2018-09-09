package hackathon.bbva.com.qrsdk.transactions

import hackathon.bbva.com.qrsdk.DaggerInjectorQR
import hackathon.bbva.com.qrsdk.transactions.domain.ConciliationAccountRequestViewModel
import hackathon.bbva.com.qrsdk.transactions.domain.ConciliationRequestViewModel
import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel
import hackathon.bbva.com.qrsdk.transactions.domain.TransactionsResponseViewModel
import hackathon.bbva.com.qrsdk.user.LoginLocalStorage
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
 * Created by Dinorah Tovar on 9/9/18
 */
class TransactionsRemoteStorage {

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
    internal fun getProductsAvailable (): Observable<List<ProductsResponseViewModel>> {
        return retrofit.create(TransactionServices::class.java)
                .getProductsAvailable(loginLocalStorage.currentUser()?.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Validate the user is available for the Login or the create account
     * @param telefono: String
     */
    internal fun getTransactionsAvailable (): Observable<List<TransactionsResponseViewModel>> {
        return retrofit.create(TransactionServices::class.java)
                .getTransactions(loginLocalStorage.currentUser()?.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    internal fun conciliations(alfa: String?, amount: Double, email: String?, idDevice: String?, clabe: String?): Observable<Any> {
        val conciliationAccountRequestViewModel = ConciliationAccountRequestViewModel(clabe)
        val conciliationRequestViewModel = ConciliationRequestViewModel(alfa, amount, conciliationAccountRequestViewModel, email, idDevice)
        return retrofit.create(TransactionServices::class.java)
                .conciliation(conciliationRequestViewModel)
    }

    /**
     * Interfaces to the web services
     */
    private interface TransactionServices {
        @GET("api/producto/user/{id}")
        fun getProductsAvailable(@Path("id") id: String?): Observable<List<ProductsResponseViewModel>>

        @GET("api/transaccion/device/{id}")
        fun getTransactions(@Path("id") id: String?): Observable<List<TransactionsResponseViewModel>>

        @POST("api/conciliacion")
        fun conciliation (@Body conciliationRequestViewModel: ConciliationRequestViewModel): Observable<Any>
    }

}