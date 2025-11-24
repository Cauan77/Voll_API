package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoDoCancelamento implements ValidadorCancelamentoDeConsulta {

    public void validar(DadosCancelamentoConsulta dados) {
        var motivo = dados.motivoCancelamento();

        if (motivo == null) {
            throw new ValidacaoException("O campo 'Motivo do cancelamento' é obrigatório ser preenchido para você continuar");
        }
    }

}
