package med.voll.api.domain.consulta.agendamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendarConsultaPacienteMesmoDiaValidator implements AgendarConsultaValidator {

    private ConsultaRepository consultaRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var primeiroHorario = consulta.getDataDe().withHour(7);
        var ultimoHorario = consulta.getDataDe().withHour(18);
        if(consultaRepository.existsByPacienteIdAndDataDeBetween(consulta.getPaciente().getId(), primeiroHorario, ultimoHorario)) {
            throw new ValidationException("Não é possivel agendar mais de uma consulta por paciente!");
        }
    }
}
