package med.voll.api.domain.consulta.agendamento;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendarConsultaDataNoFuturoValidator implements AgendarConsultaValidator {

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var hoje = LocalDateTime.now();
        var data = consulta.getDataDe();
        if(data.isBefore(hoje)) {
            throw new ValidationException("Não é possivel agendar para uma data no passado!");
        }
    }
}
