package med.voll.api.domain.consulta.agendamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.application.dto.AgendarConsultaDto;
import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AgendarConsultaMedicoInativoValidator implements AgendarConsultaValidator {

    private final MedicoRepository medicoRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        if (Objects.isNull(consulta.getMedico())) {
            return;
        }
        if (!medicoRepository.findAtivoById(consulta.getMedico().getId())) {
            throw new ValidationException("Não é possivel agendar consultas com um médico inátivo!");
        }
    }
}
