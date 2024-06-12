package IndividualPedroPinto.IndividualPedroPinto.controller

import IndividualPedroPinto.IndividualPedroPinto.dominio.Peixe
import IndividualPedroPinto.IndividualPedroPinto.dominio.Pescador
import IndividualPedroPinto.IndividualPedroPinto.service.PescadorService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/pescador")
class PescadorController(
       val pescadorService: PescadorService

) {

    @PostMapping
    fun adicionarPescador(@RequestBody @Valid novoPescador: Pescador): ResponseEntity<Pescador> {
        pescadorService.salvar(novoPescador)
        return ResponseEntity.status(201).body(novoPescador)
    }


    @PutMapping("/{id}")
    fun atualizarPescador(@PathVariable id: Int, @RequestBody @Valid novoPescador: Pescador): ResponseEntity<*>{
        val pescadorAtualizado = pescadorService.atualizar(id, novoPescador)
        return ResponseEntity.ok(pescadorAtualizado)

    }


}