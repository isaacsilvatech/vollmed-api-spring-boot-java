package med.voll.api.application.dto;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.cancelamento.MotivoCancelamento;

import java.time.LocalDateTime;

public record DetalhesConsultaDto(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime dataDe,
        LocalDateTime dataAte,
        MotivoCancelamento motivoCancelamento
) {
    public DetalhesConsultaDto(Consulta consulta) {
        this(consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getDataDe(),
                consulta.getDataAte(),
                consulta.getMotivoCancelamento());
    }
}
