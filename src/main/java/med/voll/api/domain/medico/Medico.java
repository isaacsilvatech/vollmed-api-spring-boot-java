package med.voll.api.domain.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dto.CriarMedicoDto;
import med.voll.api.application.dto.AtualizarMedicoDto;
import med.voll.api.domain.paciente.endereco.Endereco;

import java.util.Objects;

@Table(name = "medico")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public static Medico criar(CriarMedicoDto medicoDto) {
        var medico = new Medico();
        medico.nome = medicoDto.nome();
        medico.email = medicoDto.email();
        medico.crm = medicoDto.crm();
        medico.telefone = medicoDto.telefone();
        medico.especialidade = medicoDto.especialidade();
        medico.endereco = Endereco.criar(medicoDto.endereco());
        medico.ativo = true;
        return medico;
    }

    public void atualizar(AtualizarMedicoDto atualizarMedicoDto) {
        if (Objects.nonNull(atualizarMedicoDto.nome())) {
            this.nome = atualizarMedicoDto.nome();
        }
        if (Objects.nonNull(atualizarMedicoDto.telefone())) {
            this.telefone = atualizarMedicoDto.telefone();
        }
        if (Objects.nonNull(atualizarMedicoDto.endereco())) {
            this.endereco.atualizar(atualizarMedicoDto.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
