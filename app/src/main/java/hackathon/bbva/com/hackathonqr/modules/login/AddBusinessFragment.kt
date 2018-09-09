package hackathon.bbva.com.hackathonqr.modules.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.MyApplication
import hackathon.bbva.com.hackathonqr.modules.home.HomeActivity
import hackathon.bbva.com.qrsdk.user.LoginRepository
import kotlinx.android.synthetic.main.fragment_add_business.*
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/8/18
 * Add Account Fragment
 */
class AddBusinessFragment: Fragment(), AddBusinessView {

    @Inject internal lateinit var loginRepository: LoginRepository
    private var telefono: String? = null
    private var clabe: String? = null
    private var presenter: AddBusinessPresenter? = null

    /**
     * Companion object
     */
    companion object {
        const val CLABE = "clabe"
    }

    /**
     * onCreate
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.mAppComponent?.inject(this)
        activity?.title = getString(R.string.title_business)
        presenter = AddBusinessPresenter(loginRepository, this)
        val bundle = arguments
        if (bundle != null) {
            telefono = bundle.getString(AddAccountFragment.PHONE)
            clabe = bundle.getString(CLABE)
        }
    }

    /**
     * Creates the view, called from ActivityFragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_business, container, false)
    }

    /**
     * Set the view after creation
     * @param savedInstanceState
     * @param view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setView()
    }

    private fun setView() {
        add_business_get_in.setOnClickListener { presenter?.verifyData(telefono, clabe, add_business_number.text.toString(), add_business_password.text.toString()) }
    }

    override fun goToHome() {
        activity?.finish()
        startActivity(Intent (context, HomeActivity::class.java))
    }

}