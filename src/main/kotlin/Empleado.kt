import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Empleados")
class Empleado(
    @Id
    val id: Long,

    @Column(name = "Nombre")
    val nombre: String,

    @Column
    val edad: Int
)