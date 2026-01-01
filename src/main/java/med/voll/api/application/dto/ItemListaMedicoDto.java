package med.voll.api.application.dto;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

public record ItemListaMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public ItemListaMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
