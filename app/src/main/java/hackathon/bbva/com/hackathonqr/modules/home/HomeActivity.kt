package hackathon.bbva.com.hackathonqr.modules.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.MyApplication
import hackathon.bbva.com.hackathonqr.modules.accounts.AccountActivity
import hackathon.bbva.com.hackathonqr.modules.qr.QRActivity
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
     * Setting the view and container
     */
    private fun setView () {
        pay_container.setOnClickListener { startActivity(Intent (this, QRActivity::class.java)) }
        home_account_container.setOnClickListener { startActivity(Intent(this, AccountActivity::class.java)) }
    }

    override fun setNameBusiness(razonSocial: String?, account: String?) {
        val home = getString(R.string.welcome_space) + razonSocial
        val clabe = getString(R.string.home_clabe) + account
        home_name.text = home
        home_account_title.text = clabe
    }

    override fun setViewTransaction(transaction: TransactionsResponseViewModel) {
        home_account_body.text = transaction.amount.toString()
        val df = SimpleDateFormat("dd·MM·yy", Locale.getDefault())
        val formattedDate = df.format(Calendar.getInstance().time)
        home_account_sub_body.text = formattedDate
    }

}