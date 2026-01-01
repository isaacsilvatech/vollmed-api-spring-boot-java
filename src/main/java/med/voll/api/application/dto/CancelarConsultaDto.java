package med.voll.api.application.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.cancelamento.MotivoCancelamento;

public record CancelarConsultaDto(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivoCancelamento) {
}
