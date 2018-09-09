package hackathon.bbva.com.hackathonqr.modules.qr

import hackathon.bbva.com.qrsdk.transactions.TransactionsRepository
import hackathon.bbva.com.qrsdk.transactions.domain.ProductsResponseViewModel
import hackathon.bbva.com.qrsdk.user.LoginRepository

/**
 * Created by Dinorah Tovar on 9/9/18
 */
class QRConfirmationPresenter
/**
 * Constructor
 * @param view
 */
(private val loginRepository: LoginRepository, private val transactionsRepository: TransactionsRepository, private val view: QRConfirmationView) {

    fun settingProductView (products: List<ProductsResponseViewModel>?) {
        if (products != null)
            view.settingAdapter(products)
        var total = 0.0
        for (product in products!!) {
            total += product.precio!!
        }
        val iva = (16 * total)/100
        view.settingAmounts(total, iva, total+iva, products.size)
    }

    fun settingView () {
        val userDB = loginRepository.currentUser()
        view.settingView(userDB?.social)
    }

    fun settingDouble (quantity: Double) {
        val iva = (16 * quantity)/100
        view.settingAmounts(quantity, iva, quantity+iva, 0)
    }

    fun confirmation(amount: Double) {
        val clabe = loginRepository.currentUser()?.clabe
        val id = loginRepository.currentUser()?.id
        transactionsRepository.conciliations("JOSE", amount, "dinorahto@gmail.com", id, clabe).subscribe({
            view.goToHome()
        }, {})
    }

}