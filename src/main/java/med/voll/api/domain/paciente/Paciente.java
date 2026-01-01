package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dto.PacienteDto;
import med.voll.api.application.dto.AtualizarPacienteDto;
import med.voll.api.domain.endereco.Endereco;

import java.util.Objects;


@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public static Paciente criar(PacienteDto pacienteDto) {
        var paciente = new Paciente();
        paciente.nome = pacienteDto.nome();
        paciente.cpf = pacienteDto.cpf();
        paciente.email = pacienteDto.email();
        paciente.telefone = pacienteDto.telefone();
        paciente.endereco = Endereco.criar(pacienteDto.endereco());
        paciente.ativo = true;
        return paciente;
    }

    public void atualizar(AtualizarPacienteDto atualizarPacienteDto) {
        if (Objects.nonNull(atualizarPacienteDto.nome())) {
            this.nome = atualizarPacienteDto.nome();
        }
        if (Objects.nonNull(atualizarPacienteDto.telefone())) {
            this.telefone = atualizarPacienteDto.telefone();
        }
        if (Objects.nonNull(atualizarPacienteDto.endereco())) {
            this.endereco.atualizar(atualizarPacienteDto.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
