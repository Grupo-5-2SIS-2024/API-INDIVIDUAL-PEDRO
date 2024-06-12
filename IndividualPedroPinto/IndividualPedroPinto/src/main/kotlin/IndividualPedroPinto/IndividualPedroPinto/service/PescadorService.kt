package IndividualPedroPinto.IndividualPedroPinto.service


import IndividualPedroPinto.IndividualPedroPinto.dominio.Peixe
import IndividualPedroPinto.IndividualPedroPinto.dominio.Pescador
import IndividualPedroPinto.IndividualPedroPinto.repository.PescadorRepository
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException

class PescadorService (


    val pescadorRepository: PescadorRepository

    ){

    fun salvar(novoPescador: Pescador) {

        val pescadorExistente = pescadorRepository.findByCpf(novoPescador.cpf?:"")
        if (pescadorExistente != null) {
            throw ResponseStatusException(
                    HttpStatusCode.valueOf(404))
        } else {
            pescadorRepository.save(novoPescador)
            ResponseEntity.status(201).body(novoPescador)
        }
    }




    fun atualizar(id: Int ,novoPescador: Pescador): ResponseEntity<Pescador>{
        val pescadorExistente = pescadorRepository.findById(id)
        if (pescadorExistente.isPresent) {
            var pescadorEscolhido = pescadorExistente.get()

            pescadorEscolhido.nome = novoPescador.nome
            pescadorEscolhido.AnosPesca = novoPescador.AnosPesca
            pescadorEscolhido.cpf = novoPescador.cpf
            pescadorEscolhido.peixes = novoPescador.peixes



            val pescadorAtualizado = pescadorRepository.save(pescadorEscolhido)
            return ResponseEntity.status(200).body(pescadorAtualizado)
        } else {
            return ResponseEntity.status(404).build()
        }

    }





}