package IndividualPedroPinto.IndividualPedroPinto.controller

import IndividualPedroPinto.IndividualPedroPinto.dominio.Peixe
import IndividualPedroPinto.IndividualPedroPinto.service.PeixeService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/peixes")
class PeixeController(
        val peixeService: PeixeService

) {

    @GetMapping
    fun listarPeixes(): ResponseEntity<List<Peixe>> {
        val peixes = peixeService.getLista()
        return ResponseEntity.status(200).body(peixes)
    }


    @PostMapping
    fun adicionarPeixes(@RequestBody @Valid novoPeixe: Peixe): ResponseEntity<Peixe> {
        peixeService.salvar(novoPeixe)
        return ResponseEntity.status(201).body(novoPeixe)
    }


    @DeleteMapping("/{id}")
    fun deletarPeixes(@PathVariable id: Int): ResponseEntity<Peixe> {
        peixeService.deletar(id)
        return ResponseEntity.status(200).build()

    }

    @PutMapping("/{id}")
    fun atualizarPeixe(@PathVariable id: Int, @RequestBody @Valid novoPeixe: Peixe): ResponseEntity<*>{
        val peixeAtualizado = peixeService.atualizar(id, novoPeixe)
        return ResponseEntity.ok(peixeAtualizado)

    }




}