package med.voll.api.application.dto;

import med.voll.api.domain.paciente.Paciente;

public record ItemListaPacienteDto(Long id, String nome, String email, String cpf) {

    public ItemListaPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
