package Database

import android.content.Context
import android.provider.MediaStore
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [VideoDB::class], version = 1)

abstract class AppDatabase: RoomDatabase() {
    abstract fun videoDao(): VideoDAO

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "polyline_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}