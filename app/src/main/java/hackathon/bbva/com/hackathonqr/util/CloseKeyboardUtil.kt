package hackathon.bbva.com.hackathonqr.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Dinorah Tovar on 9/8/18
 * Close the keyboard from any view available
 */

object CloseKeyboardUtil {

    /**
     * Close keyboard from all the views available in the fragment or activity
     * This object must be called with a no nullable view and a not nullable context
     * @param view the parent view
     * @param context the error, can be either an Int or a String
    */
    fun closeKeyboard (view: View?, context: Context?) {
        if (view != null) {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}