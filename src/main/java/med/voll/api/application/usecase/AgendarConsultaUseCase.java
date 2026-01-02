package med.voll.api.application.usecase;

import lombok.RequiredArgsConstructor;
import med.voll.api.application.dto.AgendarConsultaDto;
import med.voll.api.application.dto.DetalhesConsultaDto;
import med.voll.api.application.exception.EspecialidadeNaoEncontradaException;
import med.voll.api.application.exception.PacienteNaoEncontradoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.agendamento.AgendarConsultaValidator;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendarConsultaUseCase {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    private final List<AgendarConsultaValidator> consultaAgendarValidators;

    @Transactional
    public DetalhesConsultaDto agendar(AgendarConsultaDto consultaDto) {

        var paciente = pacienteRepository.findById(consultaDto.idPaciente()).orElseThrow(() ->
                new PacienteNaoEncontradoException("Id do paciente informado não existe!"));

        var medico = obterMedico(consultaDto.idMedico(), consultaDto.especialidade(), consultaDto.dataDe(), consultaDto.dataAte());
        var consulta = Consulta.agendar(paciente, medico, consultaDto.dataDe(), consultaDto.dataAte());

        consultaAgendarValidators.forEach(v -> v.validate(consulta));

        return new DetalhesConsultaDto(consultaRepository.save(consulta));
    }

    private Medico obterMedico(Long idMedico, Especialidade especialidade, LocalDateTime dataDe, LocalDateTime dataAte) {
        if (Objects.nonNull(idMedico)) {
            return medicoRepository.getReferenceById(idMedico);
        }
        if (Objects.isNull(especialidade)) {
            throw new EspecialidadeNaoEncontradaException("Especialidade é obrigatória quando médico não for escolhido!");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(especialidade, dataDe, dataAte);
    }
}
