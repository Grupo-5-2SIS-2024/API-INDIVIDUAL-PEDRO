package IndividualPedroPinto.IndividualPedroPinto.repository

import IndividualPedroPinto.IndividualPedroPinto.dominio.Peixe
import org.springframework.data.jpa.repository.JpaRepository

interface PeixeRepository:JpaRepository<Peixe, Int> {

    fun findByNome(nome: String): Peixe?


}