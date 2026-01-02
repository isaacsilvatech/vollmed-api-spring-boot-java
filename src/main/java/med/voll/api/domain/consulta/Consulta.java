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

    @Column(name = "data_de")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataDe;

    @Column(name = "data_ate")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAte;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_cancelamento")
    private MotivoCancelamento motivoCancelamento;

    public static Consulta agendar(Paciente paciente, Medico medico, LocalDateTime dataDe, LocalDateTime dataAte) {
        var consulta = new Consulta();
        consulta.paciente = paciente;
        consulta.medico = medico;
        consulta.dataDe = dataDe;
        consulta.dataAte = dataAte;
        return consulta;
    }

    public void cancelar(MotivoCancelamento motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }
}
