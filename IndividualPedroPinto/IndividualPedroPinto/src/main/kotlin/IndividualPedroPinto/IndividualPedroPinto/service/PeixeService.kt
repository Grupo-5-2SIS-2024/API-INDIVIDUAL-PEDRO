package IndividualPedroPinto.IndividualPedroPinto.service

import IndividualPedroPinto.IndividualPedroPinto.dominio.Peixe
import IndividualPedroPinto.IndividualPedroPinto.repository.PeixeRepository
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException

class PeixeService(
        val peixeRepositoy: PeixeRepository


) {
    fun validarLista(lista:List<*>) {
        if (lista.isEmpty()) {
            throw ResponseStatusException(HttpStatusCode.valueOf(204))
        }
    }


    fun getLista():List<Peixe> {
        val lista = peixeRepositoy.findAll()
        validarLista(lista)

        return lista
    }


    fun salvar(novoPeixe: Peixe) {

        val peixeExistente = peixeRepositoy.findByNome(novoPeixe.nome?:"")
        if (peixeExistente != null) {
            throw ResponseStatusException(
                    HttpStatusCode.valueOf(404))
        } else {
            peixeRepositoy.save(novoPeixe)
            ResponseEntity.status(201).body(novoPeixe)
        }
    }


    fun deletar(id: Int) {
        if (!peixeRepositoy.existsById(id)) {
            throw IllegalArgumentException("Peixe não encontrado")
        }

        peixeRepositoy.deleteById(id)

    }





    fun atualizar(id: Int ,novoPeixe: Peixe): ResponseEntity<Peixe>{
        val peixeExistente = peixeRepositoy.findById(id)
        if (peixeExistente.isPresent) {
            var peixeEscolhido = peixeExistente.get()

            peixeEscolhido.nome = novoPeixe.nome
            peixeEscolhido.preco = novoPeixe.preco
            peixeEscolhido.kg = novoPeixe.kg
            peixeEscolhido.pescador = novoPeixe.pescador

            val peixeAtualizado = peixeRepositoy.save(peixeEscolhido)
            return ResponseEntity.status(200).body(peixeAtualizado)
        } else {
            return ResponseEntity.status(404).build()
        }

    }



}