package hackathon.bbva.com.hackathonqr

import javax.inject.Singleton
import dagger.Component
import hackathon.bbva.com.hackathonqr.modules.accounts.AccountActivity
import hackathon.bbva.com.hackathonqr.modules.home.HomeActivity
import hackathon.bbva.com.hackathonqr.modules.login.AddAccountFragment
import hackathon.bbva.com.hackathonqr.modules.login.AddBusinessFragment
import hackathon.bbva.com.hackathonqr.modules.login.LoginActivity
import hackathon.bbva.com.hackathonqr.modules.qr.QRActivity
import hackathon.bbva.com.hackathonqr.modules.qr.QRConfirmation
import hackathon.bbva.com.hackathonqr.modules.qr.QRProductFragment
import hackathon.bbva.com.qrsdk.DataComponent
import hackathon.bbva.com.qrsdk.DataModule

/**
 * Created by Dinorah Tovar on 2/14/18.
 * Component to inject the Data Module and the App Module
 */

@Singleton
@Component(modules = [(DataModule::class), (AppModule::class)])
interface AppComponent : DataComponent {
    fun inject (loginActivity: LoginActivity)
    fun inject (addAccountFragment: AddAccountFragment)
    fun inject (addBusinessFragment: AddBusinessFragment)
    fun inject (homeActivity: HomeActivity)
    fun inject (qrActivity: QRActivity)
    fun inject (accountActivity: AccountActivity)
    fun inject (qrProductFragment: QRProductFragment)
    fun inject (qrConfirmation: QRConfirmation)
}