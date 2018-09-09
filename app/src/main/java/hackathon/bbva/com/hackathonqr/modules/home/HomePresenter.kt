package hackathon.bbva.com.hackathonqr.modules.home

import android.util.Log
import hackathon.bbva.com.qrsdk.transactions.TransactionsRepository
import hackathon.bbva.com.qrsdk.user.LoginRepository

/**
 * Created by Dinorah Tovar on 9/8/18
 * Home Presenter for view available
 */
class HomePresenter
/**
 * Constructor
 * @param view
 */
(private val loginRepository: LoginRepository, private val transactionsRepository: TransactionsRepository, private val view: HomeView) {

    /**
     * Searches for a stored user that has logged in this phone
     */
    fun setCustomView () {
        val userDB = loginRepository.currentUser()
        val account = userDB?.clabe?.substring(userDB.clabe?.length!! - 4)
        view.setNameBusiness(userDB?.social, account)
        setTransactions()
    }

    private fun setTransactions () {
        transactionsRepository.getTransactionsAvailable().subscribe({
            view.setViewTransaction(it[0])
        }, {
            Log.e("", "")
        })
    }

}