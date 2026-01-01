package med.voll.api.domain.consulta.agendamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.application.dto.AgendarConsultaDto;
import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendarConsultaPacienteInativoValidator implements AgendarConsultaValidator {

    private PacienteRepository pacienteRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        if(!pacienteRepository.findAtivoById(consulta.getPaciente().getId())) {
            throw new ValidationException("Não é possivel agendar consultas com um paciente inativo!");
        }
    }
}
