package med.voll.api.application.dto;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.cancelamento.MotivoCancelamento;

import java.time.LocalDateTime;

public record DatalhesConsultaDto(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data,
        MotivoCancelamento motivoCancelamento
) {
    public DatalhesConsultaDto(Consulta consulta) {
        this(consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getData(),
                consulta.getMotivoCancelamento());
    }
}
