package IndividualPedroPinto.IndividualPedroPinto.repository



import IndividualPedroPinto.IndividualPedroPinto.dominio.Pescador
import org.springframework.data.jpa.repository.JpaRepository

interface PescadorRepository: JpaRepository<Pescador, Int> {

    fun findByCpf(cpf: String): Pescador?

}