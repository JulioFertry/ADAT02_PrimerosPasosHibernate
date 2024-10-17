import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import java.time.Instant
import java.util.*


fun main() {

    // Cargar la configuración que queremos del persistence.xml
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    // Abre la conexión con la base de datos -> em contiene la conexión
    var em: EntityManager = emf.createEntityManager()

    // Si queremos empezar una transacción...
    em.transaction.begin()

    val fecha = Date.from(Instant.now())
    val dptoIT = Departamento("IT", fecha, null)

    val emple1 = Empleado("Pepe", 37, dptoIT, null)
    val emple2 = Empleado("Eustaquio", 22, dptoIT, null)
    em.persist(dptoIT)
    em.persist(emple1) // .persist() persiste el objeto en un PersistenceContext
    em.persist(emple2)

    // Empujamos los cambios a la base de datos, hasta que no se hace el commit no persisten los objetos en la BD
    em.transaction.commit()

    // Cerrar la conexión con la Base de Datos
    em.close()

    // Nueva transacción
    em = emf.createEntityManager()
    em.transaction.begin()
    val managedDptoIT = em.merge(dptoIT)
    val emple3 = Empleado("Juan", 54, managedDptoIT, null)
    em.persist(emple3)
    em.transaction.commit()
    em.close()

}