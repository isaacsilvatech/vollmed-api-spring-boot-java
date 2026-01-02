package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataDeBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndDataDeLessThanEqualAndDataAteGreaterThanEqual(Long medicoId, LocalDateTime dataDe, LocalDateTime dataAte);
}
