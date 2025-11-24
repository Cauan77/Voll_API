package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idPaciente() == null) {
            return;
        }

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        Boolean pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if (pacienteEstaAtivo == null || !pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo!");
        }
    }
}
