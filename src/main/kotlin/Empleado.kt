import jakarta.persistence.*

@Entity
@Table(name = "Empleados")
class Empleado(

    @Column(name = "Nombre")
    val nombre: String,

    @Column
    val edad: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_dpto")
    val dpto: Departamento,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Columna autoincremental de la BBDD
    val id: Long?,
)