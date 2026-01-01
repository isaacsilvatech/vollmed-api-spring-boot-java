package med.voll.api.application.dto;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

public record DetalhesMedicoDto(Long id, String name, String email, String crm, String telefone,
                                Especialidade especialidade, EnderecoDto endereco) {

    public DetalhesMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
                medico.getEspecialidade(), new EnderecoDto(medico.getEndereco()));
    }
}
