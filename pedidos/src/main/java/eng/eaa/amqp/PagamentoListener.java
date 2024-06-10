package eng.eaa.amqp;

import eng.eaa.dto.PagamentoDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

//    @RabbitListener(queues = "pagamento.concluido")
    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void recebeMensagem(PagamentoDto pagamentoDto){
        String mensagem = """
                Dados do pagamento: %s
                Número do pedido: %s
                Valor R$: %s
                Status: %s
                """.formatted(pagamentoDto.getId(), pagamentoDto.getPedidoId(), pagamentoDto.getValor(), pagamentoDto.getStatus());

        System.out.println(mensagem);
    }


}
