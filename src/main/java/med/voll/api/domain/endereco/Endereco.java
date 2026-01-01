package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dto.EnderecoDto;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public static Endereco criar(EnderecoDto enderecoDto) {
        var endereco = new Endereco();
        endereco.logradouro = enderecoDto.logradouro();
        endereco.bairro = enderecoDto.bairro();
        endereco.cep = enderecoDto.cep();
        endereco.cidade = enderecoDto.cidade();
        endereco.uf = enderecoDto.uf();
        endereco.numero = enderecoDto.numero();
        endereco.complemento = enderecoDto.complemento();
        return endereco;
    }

    public void atualizar(EnderecoDto endereco) {
        if (Objects.nonNull(endereco.logradouro())) {
            this.logradouro = endereco.logradouro();
        }
        if (Objects.nonNull(endereco.bairro())) {
            this.bairro = endereco.bairro();
        }
        if (Objects.nonNull(endereco.cep())) {
            this.cep = endereco.cep();
        }
        if (Objects.nonNull(endereco.cidade())) {
            this.cidade = endereco.cidade();
        }
        if (Objects.nonNull(endereco.uf())) {
            this.uf = endereco.uf();
        }
        if (Objects.nonNull(endereco.numero())) {
            this.numero = endereco.numero();
        }
        if (Objects.nonNull(endereco.complemento())) {
            this.complemento = endereco.complemento();
        }
    }
}
