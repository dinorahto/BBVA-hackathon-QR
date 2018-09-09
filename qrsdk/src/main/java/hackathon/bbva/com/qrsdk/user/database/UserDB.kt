package hackathon.bbva.com.qrsdk.user.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by Dinorah Tovar on 9/7/18
 * User DB class to manage the user basic info about the user
*/

@RealmClass
open class UserDB(
    @PrimaryKey
    var id: String? = null,
    var phone: String? = null,
    var social: String? = null,
    var clabe: String? = null,
    var name: String? = null
): RealmObject()