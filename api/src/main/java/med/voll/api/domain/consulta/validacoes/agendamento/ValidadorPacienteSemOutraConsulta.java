package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsulta implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primerioHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutroAgendamentoNoMesmoHorario = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primerioHorario, ultimoHorario);

        if (pacientePossuiOutroAgendamentoNoMesmoHorario) {
            throw new ValidacaoException("O paciente já tem uma consulta agendada nesse mesmo horário");
        }
    }
}
