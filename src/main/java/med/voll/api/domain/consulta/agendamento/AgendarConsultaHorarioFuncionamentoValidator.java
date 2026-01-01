package med.voll.api.domain.consulta.agendamento;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class AgendarConsultaHorarioFuncionamentoValidator implements AgendarConsultaValidator {

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var data = consulta.getData();
        var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesHorarioFuncionamento = data.getHour() < 7;
        var depoisHorarioFuncionamento = data.getHour() > 18;
        var depoisMinutosHorarioFuncionamento = (data.getHour() == 18 && data.getMinute() > 0);

        if (domingo || antesHorarioFuncionamento || depoisHorarioFuncionamento || depoisMinutosHorarioFuncionamento) {
            throw new ValidationException("A consulta deve ser agendada dentro do hórario de funcionamento!");
        }
    }
}
