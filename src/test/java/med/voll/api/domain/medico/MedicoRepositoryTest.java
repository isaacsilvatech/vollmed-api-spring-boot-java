package med.voll.api.domain.medico;

import med.voll.api.application.dto.CriarMedicoDto;
import med.voll.api.application.dto.CriarPacienteDto;
import med.voll.api.application.dto.EnderecoDto;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    public void escolherMedicoAleatorioNaDataCase01() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 30);
        var proximaSegundaAs10AteAs11 = proximaSegundaAs10.plusHours(1);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        agendarConsulta(medico, paciente, proximaSegundaAs10, proximaSegundaAs10AteAs11);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10, proximaSegundaAs10AteAs11);

        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 30);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10, proximaSegundaAs10.plusHours(1));

        assertThat(medicoLivre).isEqualTo(medico);
    }

    private Consulta agendarConsulta(Medico medico, Paciente paciente, LocalDateTime dataDe, LocalDateTime dataAte) {
        var consulta = Consulta.agendar(paciente, medico, dataDe, dataAte);
        em.persist(consulta);
        return consulta;
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = Medico.criar(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = Paciente.criar(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private CriarMedicoDto dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new CriarMedicoDto(
                nome,
                email,
                crm,
                "61999999999",
                especialidade,
                dadosEndereco()
        );
    }

    private CriarPacienteDto dadosPaciente(String nome, String email, String cpf) {
        return new CriarPacienteDto(
                nome,
                cpf,
                email,
                "61999999999",
                dadosEndereco()
        );
    }

    private EnderecoDto dadosEndereco() {
        return new EnderecoDto(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}