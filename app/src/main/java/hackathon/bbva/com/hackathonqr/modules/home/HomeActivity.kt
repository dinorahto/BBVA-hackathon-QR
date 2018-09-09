package hackathon.bbva.com.hackathonqr.modules.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.MyApplication
import hackathon.bbva.com.hackathonqr.modules.accounts.AccountActivity
import hackathon.bbva.com.hackathonqr.modules.qr.QRActivity
import hackathon.bbva.com.hackathonqr.util.MoneyUtils
import hackathon.bbva.com.qrsdk.transactions.TransactionsRepository
import hackathon.bbva.com.qrsdk.transactions.domain.TransactionsResponseViewModel
import hackathon.bbva.com.qrsdk.user.LoginRepository
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/8/18
 */

class HomeActivity: AppCompatActivity(), HomeView {

    @Inject internal lateinit var loginRepository: LoginRepository
    @Inject internal lateinit var transactionRepository: TransactionsRepository
    private var presenter: HomePresenter? = null

    /**
     * Companion Object for constants
     */
    companion object {
        const val SUCCESS_VERIFY = 2020
        const val RELOAD_BALANCE = 2021
    }

    /**
     * Starting Activity from Login Activity
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.mAppComponent?.inject(this)
        setContentView(R.layout.activity_main)
        presenter = HomePresenter (loginRepository,transactionRepository, this)
        setView()
        presenter?.setCustomView()
    }

    /**
     * On Activity result of Verify Activity after a successful email verification or
     * a Profile interaction
     * @param requestCode Int
     * @param resultCode Int
     * @param data Intent
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RELOAD_BALANCE -> if (resultCode == RESULT_OK) {
                presenter?.setCustomView()
                scrollView()
            }
        }
    }

    /**
     * Setting the view and container
     */
    private fun setView () {
        pay_container.setOnClickListener { startActivityForResult(Intent (this, QRActivity::class.java), RELOAD_BALANCE) }
        home_account_container.setOnClickListener { startActivity(Intent(this, AccountActivity::class.java)) }
        historial_container.setOnClickListener { startActivity(Intent(this, AccountActivity::class.java)) }
    }

    private fun scrollView () {
        main_scroll_view.smoothScrollTo(0, account_container.bottom)
    }

    override fun setNameBusiness(razonSocial: String?, account: String?) {
        val home = getString(R.string.welcome_space) + razonSocial
        val clabe = getString(R.string.home_clabe) + account
        home_name.text = home
        home_account_title.text = clabe
    }

    override fun setViewTransaction(transaction: TransactionsResponseViewModel) {
        home_account_body.text = MoneyUtils.setCurrency(transaction.amount!!)
        val df = SimpleDateFormat("dd·MM·yy", Locale.getDefault())
        val formattedDate = df.format(Calendar.getInstance().time)
        home_account_sub_body.text = formattedDate
    }

}