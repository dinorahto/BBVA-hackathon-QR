package hackathon.bbva.com.hackathon_qr

import android.app.Application
import dagger.Module
import hackathon.bbva.com.qrsdk.DataModule
import hackathon.bbva.com.qrsdk.Injector

/**
 * Created by Dinorah Tovar on 2/14/17.
 * My Application
 */

@Module
class MyApplication : Application {

    private var mApplication: Application? = null

    /**
     * Companion Object with static objects
     */
    companion object {
        var mAppComponent: AppComponent? = null
    }

    /**
     * Empty constructor
     */
    constructor()

    /**
     * Constructor with Application
     * @param mApplication the application
     */
    constructor(mApplication: Application) {
        this.mApplication = mApplication
    }

    /**
     * Initializes Fabric, Realm and Dagger component
     */
    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule(""))
                .build()
        Injector.context = this
        Injector.dataComponent = mAppComponent
    }

}