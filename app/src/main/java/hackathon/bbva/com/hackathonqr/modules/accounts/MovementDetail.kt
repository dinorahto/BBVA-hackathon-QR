package hackathon.bbva.com.hackathonqr.modules.accounts

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hackathon.bbva.com.hackathon_qr.R
import kotlinx.android.synthetic.main.fragment_movement_detail.*

/**
 * Created by Dinorah Tovar on 9/8/18
 */

class MovementDetail: Fragment() {

    /**
     * onCreate
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Creates the view, called from ActivityFragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movement_detail, container, false)
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
        movement_detail_share.setOnClickListener { inviteFriends() }
    }

    /**
     * Shares the app with friends
     */
    private fun inviteFriends() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val body = "Hello BBVA Hackathon"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, resources.getString(R.string.app_name))
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body)
        startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.app_name)))
    }

}