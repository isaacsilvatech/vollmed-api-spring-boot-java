package med.voll.api.application.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarPacienteDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco
) {
}
