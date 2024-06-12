import IndividualPedroPinto.IndividualPedroPinto.dominio.Pescador
import IndividualPedroPinto.IndividualPedroPinto.repository.PescadorRepository
import IndividualPedroPinto.IndividualPedroPinto.service.PescadorService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.springframework.web.server.ResponseStatusException

class PescadorServiceTest {

    private val pescadorRepository = Mockito.mock(PescadorRepository::class.java)
    private val pescadorService = PescadorService(pescadorRepository)

    @Test
    fun `test salvar with existing fisherman throws exception`() {
        val pescador = Pescador(nome = "Pescador A", cpf = "12345678900", AnosPesca = 10, peixes = emptyList())
        Mockito.`when`(pescadorRepository.findByCpf("12345678900")).thenReturn(pescador)

        assertThrows<ResponseStatusException> {
            pescadorService.salvar(pescador)
        }
    }

    @Test
    fun `test salvar with new fisherman saves successfully`() {
        val pescador = Pescador(nome = "Pescador A", cpf = "12345678900", AnosPesca = 10, peixes = emptyList())
        Mockito.`when`(pescadorRepository.findByCpf("12345678900")).thenReturn(null)

        pescadorService.salvar(pescador)

        Mockito.verify(pescadorRepository, Mockito.times(1)).save(pescador)
    }

    @Test
    fun `test atualizar with non-existing fisherman returns 404`() {
        Mockito.`when`(pescadorRepository.findById(1)).thenReturn(Optional.empty())

        val pescador = Pescador(nome = "Pescador A", cpf = "12345678900", AnosPesca = 10, peixes = emptyList())
        val response = pescadorService.atualizar(1, pescador)

        assertEquals(404, response.statusCodeValue)
    }

    @Test
    fun `test atualizar with existing fisherman updates successfully`() {
        val pescadorExistente = Pescador(nome = "Pescador B", cpf = "12345678900", AnosPesca = 5, peixes = emptyList())
        Mockito.`when`(pescadorRepository.findById(1)).thenReturn(Optional.of(pescadorExistente))

        val pescador = Pescador(nome = "Pescador A", cpf = "12345678900", AnosPesca = 10, peixes = emptyList())
        val response = pescadorService.atualizar(1, pescador)

        assertEquals(200, response.statusCodeValue)
        Mockito.verify(pescadorRepository, Mockito.times(1)).save(pescadorExistente)
    }
}
