import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Transaccion::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transaccionDao(): TransaccionDao
}
