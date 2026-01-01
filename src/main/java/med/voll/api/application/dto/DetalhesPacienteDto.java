package med.voll.api.application.dto;

import med.voll.api.domain.paciente.Paciente;

public record DetalhesPacienteDto(Long id, String nome, String email, String cpf, String telefone,
                                  EnderecoDto endereco) {

    public DetalhesPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(),
                new EnderecoDto(paciente.getEndereco()));
    }
}
