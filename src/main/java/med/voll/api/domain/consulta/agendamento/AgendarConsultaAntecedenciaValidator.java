package med.voll.api.domain.consulta.agendamento;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AgendarConsultaAntecedenciaValidator implements AgendarConsultaValidator {

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var agora = LocalDateTime.now();
        var data = consulta.getDataDe();
        var diferenca = Duration.between(agora, data).toMinutes();
        if (diferenca > 0 && diferenca < 30) {
            throw new ValidationException("A consulta deve ser agendada com antecedência minima de 30 minutos!");
        }
    }
}
