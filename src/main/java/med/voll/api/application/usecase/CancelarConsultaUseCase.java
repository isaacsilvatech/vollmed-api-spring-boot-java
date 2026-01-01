package med.voll.api.application.usecase;

import lombok.RequiredArgsConstructor;
import med.voll.api.application.exception.EspecialidadeNaoEncontradaException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.application.dto.CancelarConsultaDto;
import med.voll.api.domain.consulta.cancelamento.ConsultaCancelarValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CancelarConsultaUseCase {

    private final ConsultaRepository consultaRepository;
    private final List<ConsultaCancelarValidator> consultaCancelarValidators;

    @Transactional
    public Consulta cancelar(CancelarConsultaDto cancelarConsultaDto) {
        var consulta = consultaRepository.findById(cancelarConsultaDto.idConsulta()).orElseThrow(() -> new EspecialidadeNaoEncontradaException("Consulta não encontrada!"));
        consultaCancelarValidators.forEach(v -> v.validate(consulta));
        consulta.cancelar(cancelarConsultaDto.motivoCancelamento());
        return consulta;
    }
}
