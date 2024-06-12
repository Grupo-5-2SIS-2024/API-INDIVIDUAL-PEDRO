import IndividualPedroPinto.IndividualPedroPinto.dominio.Peixe
import IndividualPedroPinto.IndividualPedroPinto.repository.PeixeRepository
import IndividualPedroPinto.IndividualPedroPinto.service.PeixeService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.springframework.web.server.ResponseStatusException

class PeixeServiceTest {

    private val peixeRepository = Mockito.mock(PeixeRepository::class.java)
    private val peixeService = PeixeService(peixeRepository)

    @Test
    fun `test getLista with empty list throws exception`() {
        Mockito.`when`(peixeRepository.findAll()).thenReturn(emptyList())

        assertThrows<ResponseStatusException> {
            peixeService.getLista()
        }
    }

    @Test
    fun `test salvar with existing fish throws exception`() {
        val peixe = Peixe(nome = "Peixe A", preco = 10.0, kg = 1.0, pescador = "pepe")
        Mockito.`when`(peixeRepository.findByNome("Peixe A")).thenReturn(peixe)

        assertThrows<ResponseStatusException> {
            peixeService.salvar(peixe)
        }
    }

    @Test
    fun `test salvar with new fish saves successfully`() {
        val peixe = Peixe(nome = "Peixe A", preco = 10.0, kg = 1.0, pescador = "pepe")
        Mockito.`when`(peixeRepository.findByNome("Peixe A")).thenReturn(null)

        peixeService.salvar(peixe)

        Mockito.verify(peixeRepository, Mockito.times(1)).save(peixe)
    }

    @Test
    fun `test deletar with non-existing id throws exception`() {
        Mockito.`when`(peixeRepository.existsById(1)).thenReturn(false)

        assertThrows<IllegalArgumentException> {
            peixeService.deletar(1)
        }
    }

    @Test
    fun `test deletar with existing id deletes successfully`() {
        Mockito.`when`(peixeRepository.existsById(1)).thenReturn(true)

        peixeService.deletar(1)

        Mockito.verify(peixeRepository, Mockito.times(1)).deleteById(1)
    }
}
