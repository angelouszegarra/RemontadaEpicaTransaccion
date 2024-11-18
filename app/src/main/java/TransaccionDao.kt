import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransaccionDao {
    @Insert
    suspend fun insertarTransaccion(transaccion: Transaccion)

    @Query("SELECT * FROM transacciones")
    suspend fun obtenerTransacciones(): List<Transaccion>
}
