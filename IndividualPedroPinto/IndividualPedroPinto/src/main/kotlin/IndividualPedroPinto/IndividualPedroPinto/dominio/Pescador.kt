package IndividualPedroPinto.IndividualPedroPinto.dominio

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.br.CPF

@Entity
data class Pescador(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int,

        @field:Size(min = 3)
        var nome: String?,

        var AnosPesca:Int,

        @CPF
        @field:Size(min = 11, max = 11)
        var cpf:String?,

        @field:OneToMany(mappedBy = "pescador")
        var peixes : List<Peixe>? = null


        )
