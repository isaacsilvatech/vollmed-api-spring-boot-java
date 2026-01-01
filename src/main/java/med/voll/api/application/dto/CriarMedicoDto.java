package med.voll.api.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.medico.Especialidade;

public record CriarMedicoDto(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String telefone,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        EnderecoDto endereco) {
}
