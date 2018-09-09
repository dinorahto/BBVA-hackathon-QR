package hackathon.bbva.com.hackathonqr.modules.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.MyApplication
import hackathon.bbva.com.hackathonqr.modules.home.HomeActivity
import hackathon.bbva.com.hackathonqr.util.CloseKeyboardUtil
import hackathon.bbva.com.qrsdk.DaggerInjectorQR.Companion.context
import hackathon.bbva.com.qrsdk.user.LoginRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_add_account.*
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/8/18
 * Login Activity
 */
class LoginActivity: AppCompatActivity(), LoginView {

    @Inject internal lateinit var loginRepository: LoginRepository
    private var presenter: LoginPresenter? = null

    /**
     * Starting Activity from Login Activity
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.mAppComponent?.inject(this)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(loginRepository, this)
        presenter?.searchUser()
        title = ""
        onBackStackChangedListener()
    }

    /**
     * On Back Stack Changed Listener
     * Checks the listener for all the view
     * knows what view is on the top of everything
     */
    private fun onBackStackChangedListener () {
        supportFragmentManager.addOnBackStackChangedListener {
            CloseKeyboardUtil.closeKeyboard(currentFocus, context)
            val addAccountFragment = supportFragmentManager.findFragmentByTag(AddAccountFragment::class.java.simpleName)
            if (addAccountFragment is AddAccountFragment) {
                title = getString(R.string.title_card)
                addAccountFragment.add_account_next.visibility = View.VISIBLE
            } else {
                title = ""
                //presenter?.searchUser()
            }

            val addBusinessFragment = supportFragmentManager.findFragmentByTag(AddBusinessFragment::class.java.simpleName)
            if (addBusinessFragment is AddBusinessFragment)
                title = getString(R.string.title_business)
        }
    }

    /**
     * Set Listener for new user without account new
     */
    private fun setListenerForAccount () {
        login_get_in.setOnClickListener {
            presenter?.validaUser(login_number_account.text.toString())
        }
    }

    /**
     * Set view for login
     */
    override fun setLogin() {
        login_welcome.visibility = View.VISIBLE
        login_name_store.visibility = View.VISIBLE
        login_password_layout.visibility = View.VISIBLE
        login_button.visibility = View.VISIBLE
        login_forgot_password.visibility = View.VISIBLE

        login_get_in.visibility = View.GONE
        login_privacy.visibility = View.GONE
        login_process_data.visibility = View.GONE
        login_process_account.visibility = View.GONE
        login_number_account_layout.visibility = View.GONE

        login_button.setOnClickListener {
            finish()
            startActivity(Intent (context, HomeActivity::class.java))
        }
    }

    /**
     * Set view for create account
     */
    override fun setAccount() {
        login_get_in.visibility = View.VISIBLE
        login_privacy.visibility = View.VISIBLE
        login_process_data.visibility = View.VISIBLE
        login_process_account.visibility = View.VISIBLE
        login_number_account_layout.visibility = View.VISIBLE
        setListenerForAccount()
    }

    override fun createAccountFirstStep(telefono: String?) {
        login_get_in.visibility = View.GONE
        val addAccountFragment = AddAccountFragment()
        val data = Bundle()
        data.putString(AddAccountFragment.PHONE, telefono)
        addAccountFragment.arguments = data
        supportFragmentManager?.beginTransaction()
              ?.add(R.id.login_container, addAccountFragment, AddAccountFragment::class.java.simpleName)
              ?.addToBackStack(null)
              ?.commit()
    }

}