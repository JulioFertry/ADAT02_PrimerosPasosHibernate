import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "Departamentos")
data class Departamento(

    @Column(name = "nombre", nullable = false, unique = true)
    val nombre: String,

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    val fechaCreacion: Date,

    @OneToOne(mappedBy = "dpto")
    val empleado: Empleado?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Columna autoincremental de la BBDD
    val numDpt: Long?
)