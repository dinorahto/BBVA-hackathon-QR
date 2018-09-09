package hackathon.bbva.com.hackathonqr

import android.app.Application
import dagger.Module
import hackathon.bbva.com.qrsdk.DataModule
import hackathon.bbva.com.qrsdk.DaggerInjectorQR
import io.realm.Realm
import io.realm.RealmConfiguration

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

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)

        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule("http://138.68.16.213:8080/"))
                .build()

        DaggerInjectorQR.context = this
        DaggerInjectorQR.dataComponent = mAppComponent
    }

}