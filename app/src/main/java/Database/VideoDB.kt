package Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class VideoDB : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    var title: String? = null

    var desc: String? = null

    var url: String? = null

    var comentOnOff: Int? = null

    var saveOnOff: Int? = null
}