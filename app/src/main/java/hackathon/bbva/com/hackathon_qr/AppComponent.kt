package hackathon.bbva.com.hackathon_qr

import javax.inject.Singleton
import dagger.Component
import hackathon.bbva.com.qrsdk.DataComponent
import hackathon.bbva.com.qrsdk.DataModule

/**
 * Created by Dinorah Tovar on 2/14/18.
 * Component to inject the Data Module and the App Module
 */

@Singleton
@Component(modules = [(DataModule::class), (AppModule::class)])
interface AppComponent : DataComponent {

}