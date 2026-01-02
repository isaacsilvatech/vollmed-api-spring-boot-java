package med.voll.api.domain.consulta.cancelamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.application.dto.CancelarConsultaDto;
import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ConsultaCancelarAntecedenciaValidator implements ConsultaCancelarValidator {

    private final ConsultaRepository consultaRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var agora = LocalDateTime.now();
        var data = consulta.getDataDe();
        var diferenca = Duration.between(agora, data).toHours();
        if (diferenca > 0 && diferenca < 24) {
            throw new ValidationException("A consulta só pode ser cancelada com antecedência minima de 24 horas!");
        }
    }
}
