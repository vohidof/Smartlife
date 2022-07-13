package Model

import java.io.Serializable

class UserModel : Serializable {
    var uid : String = ""
    var name : String = ""
    var surname : String = ""
    var phone : String = ""
    var email : String = ""
    var password : String = ""
    var imgUrl : String = ""

    constructor(
        uid: String,
        name: String,
        surname: String,
        phone: String,
        email: String,
        password: String,
        imgUrl: String
    ) {
        this.uid = uid
        this.name = name
        this.surname = surname
        this.phone = phone
        this.email = email
        this.password = password
        this.imgUrl = imgUrl
    }

    constructor()
}