package Database

import Model.MyVideo
import android.app.Person
import androidx.room.*

@Dao
interface VideoDAO {
    @Transaction
    @Query("select * from videodb")
    fun getAllVideo(): List<VideoDB>

    @Insert
    fun addVideo(videoDB: VideoDB)

    @Delete
    fun deletePerson(videoDB: VideoDB)
}