package Model

import java.io.Serializable

class MyVideo : Serializable{
    var title: String? = null
    var desc: String? = null
    var url: String? = null
    var comentOnOff: Int? = null
    var saveOnOff: Int? = null


    constructor(title: String?, desc: String?, url: String?, comentOnOff: Int?, saveOnOff: Int?) {
        this.title = title
        this.desc = desc
        this.url = url
        this.comentOnOff = comentOnOff
        this.saveOnOff = saveOnOff
    }
    constructor()
}