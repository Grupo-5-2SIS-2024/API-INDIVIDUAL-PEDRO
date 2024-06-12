package IndividualPedroPinto.IndividualPedroPinto.dominio

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne


@Entity
data class Peixe(

        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        var nome: String? = null,
        var preco:Double? = null,
        var kg:Double? = null,

        @field:ManyToOne
        var pescador:Pescador? = null

)
