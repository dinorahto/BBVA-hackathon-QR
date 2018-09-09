package hackathon.bbva.com.hackathonqr.modules.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hackathon.bbva.com.hackathon_qr.R
import hackathon.bbva.com.hackathonqr.MyApplication
import kotlinx.android.synthetic.main.fragment_add_account.*

/**
 * Created by Dinorah Tovar on 9/8/18
 * Add Account Fragment
 */
class AddAccountFragment: Fragment(), AddAccountView {

    private var telefono: String? = null
    private var presenter: AddAccountPresenter? = null

    /**
     * Companion object
     */
    companion object {
        const val PHONE = "phone"
    }
    /**
     * onCreate
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.mAppComponent?.inject(this)
        activity?.title = getString(R.string.title_card)
        presenter = AddAccountPresenter(this)
        val bundle = arguments
        if (bundle != null)
            telefono = bundle.getString(PHONE)
    }

    /**
     * Creates the view, called from ActivityFragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_account, container, false)
    }

    /**
     * Set the view after creation
     * @param savedInstanceState
     * @param view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setView()
    }

    private fun setView () {
        add_account_next.setOnClickListener { presenter?.verifyData(telefono, add_account_number.text.toString())}
    }

    override fun createAccountNextStep(telefono: String?, clabe: String?) {
        add_account_next.visibility = View.GONE
        val addBusinessFragment = AddBusinessFragment()
        val data = Bundle()
        data.putString(AddAccountFragment.PHONE, telefono)
        data.putString(AddBusinessFragment.CLABE, clabe)
        addBusinessFragment.arguments = data
        fragmentManager?.beginTransaction()
                ?.add(R.id.add_account_container, addBusinessFragment, AddBusinessFragment::class.java.simpleName)
                ?.addToBackStack(null)
                ?.commit()
    }

}