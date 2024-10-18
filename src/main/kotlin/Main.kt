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

    val dptoIT = Departamento("IT", fecha, null, null)
    em.persist(dptoIT) // .persist() persiste el objeto en un PersistenceContext

    val emple1 = Empleado("Pepe", 37, dptoIT, null)
    em.persist(emple1) // .persist() persiste el objeto en un PersistenceContext

    // Empujamos los cambios a la base de datos, hasta que no se hace el commit no persisten los objetos en la BD
    em.transaction.commit()

    // Cerrar la conexión con la Base de Datos
    em.close()


    // Segunda transacción TODO: Arreglar la transacción
    // Vuelve a abrir la conexión con la base de datos -> em contiene la conexión
    em = emf.createEntityManager()

    // Abrimos la transacción
    em.transaction.begin()

    // Recuperamos el departamento que ya está en la BD para que esté "attached" al contexto de persistencia
    val dptoItAttached = em.find(Departamento::class.java, dptoIT.numDpt)

    // Creamos el nuevo empleado agregandole el departamento "attached"
    val emple2 = Empleado("Eustaquio", 22, dptoItAttached, null)

    // Persistimos el nuevo empleado
    em.persist(emple2)

    // Empujamos a la base de datos
    em.transaction.commit()

    // Cerramos la conexión con la BD
    em.close()

}