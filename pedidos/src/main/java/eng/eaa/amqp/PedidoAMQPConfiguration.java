package eng.eaa.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoAMQPConfiguration {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue filaDetalhesPedidos(){
        return QueueBuilder.nonDurable("pagamentos.detalhes-pedido").build();
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange("pagamentos.ex").build();
    }

    @Bean
    public Binding bindingPagamentoPedido(FanoutExchange fanoutExchange){
        return BindingBuilder.bind(filaDetalhesPedidos()).to(fanoutExchange);
    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn){
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin (RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

}
