package med.voll.api.domain.consulta.agendamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendarConsultaMedicoComConsultaNaDataValidator implements AgendarConsultaValidator {

    private final ConsultaRepository consultaRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        if(consultaRepository.existsByMedicoIdAndData(consulta.getPaciente().getId(), consulta.getData())) {
            throw new ValidationException("Não é possivel agendar uma consulta para um médico com uma consulta nesse mesmo hórario!");
        }
    }
}
