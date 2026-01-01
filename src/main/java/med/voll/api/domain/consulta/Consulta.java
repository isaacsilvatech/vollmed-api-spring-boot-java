package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.consulta.cancelamento.MotivoCancelamento;

import java.time.LocalDateTime;

@Table(name = "consulta")
@Entity(name = "Consulta")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public static Consulta agendar(Paciente paciente, Medico medico, LocalDateTime data) {
        var consulta = new Consulta();
        consulta.paciente = paciente;
        consulta.medico = medico;
        consulta.data = data;
        return consulta;
    }

    public void cancelar(MotivoCancelamento motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }
}
