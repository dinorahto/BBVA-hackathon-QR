package hackathon.bbva.com.qrsdk.user

import hackathon.bbva.com.qrsdk.DaggerInjectorQR
import hackathon.bbva.com.qrsdk.user.database.UserDB
import hackathon.bbva.com.qrsdk.user.domain.LoginResponseViewModel
import io.realm.Realm
import java.util.*
import javax.inject.Inject

/**
 * Created by Dinorah Tovar on 9/7/18
 * Login Local Storage Data layer
 */
class LoginLocalStorage {

    @Inject internal lateinit var realm: Realm

    /**
     * Injects Dagger on Construction
     */
    init {
        DaggerInjectorQR.dataComponent?.inject(this)
    }

    /**
     * Search for current User
     * @return UserDB
     */
    fun currentUser(): UserDB? {
        try {
            val user = realm.where(UserDB::class.java).findFirst()
            if (user != null)
                return realm.copyFromRealm(user)
        } catch (ex: Exception) { }
        return null
    }

    /**
     * Save Local DB
     * Saving all the data to the database
     * @param loginResponseViewModel: LoginResponseViewModel
     */
    fun saveLocalUserDB (loginResponseViewModel: LoginResponseViewModel) {
        val realm = Realm.getDefaultInstance()
        val userDB = UserDB()
        val user = realm.where(UserDB::class.java).findFirst()
        if (user != null)
            userDB.id = user.id
        else
            userDB.id = UUID.randomUUID().toString()
        userDB.phone = loginResponseViewModel.telefono
        userDB.name = loginResponseViewModel.nombre
        userDB.social = loginResponseViewModel.razonSocial
        userDB.clabe = loginResponseViewModel.cuentas?.get(0)?.clabe
        realm.executeTransaction { _ -> realm.copyToRealmOrUpdate(userDB) }
        realm.close()
    }

}