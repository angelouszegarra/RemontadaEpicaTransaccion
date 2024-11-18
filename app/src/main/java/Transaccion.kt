import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transacciones")
data class Transaccion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val monto: Double,
    val fecha: String,
    val receptor: String
)
