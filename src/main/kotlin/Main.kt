import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence


fun main() {

    // Cargar la configuración que queremos del persistence.xml
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    // Abre la conexión con la base de datos -> em contiene la conexión
    var em: EntityManager = emf.createEntityManager()

    // Si queremos empezar una transacción...
    em.transaction.begin()

    val emple1 = Empleado(123, "Pepe", 37)
    val emple2 = Empleado(456, "Eustaquio", 22)
    em.persist(emple1) // .persist() persiste el objeto en un PersistenceContext
    em.persist(emple2)

    // Empujamos los cambios a la base de datos, hasta que no se hace el commit no persisten los objetos en la BD
    em.transaction.commit()

    // Cerrar la conexión con la Base de Datos
    em.close()

    // Nueva transacción
    em = emf.createEntityManager()
    em.transaction.begin()
    val emple3 = Empleado(789, "Juan", 54)
    em.persist(emple3)
    em.transaction.commit()
    em.close()
}