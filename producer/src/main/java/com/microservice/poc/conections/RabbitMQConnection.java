package com.microservice.poc.conections;

import constantes.RabbitmqConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin ampqAdmin) {
        this.amqpAdmin = ampqAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(this.NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca) {
       return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    // Após a construção da classe, irá acionar o metodo.
    @PostConstruct
    private void adiciona() {
        Queue filaEstoque = this.fila(RabbitmqConstantes.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitmqConstantes.FILA_PRECO);

        DirectExchange troca = this.trocaDireta();

        Binding relacionaEstoque = this.relacionamento(filaEstoque, troca);
        Binding relacionaPreco = this.relacionamento(filaPreco, troca);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(relacionaEstoque);
        this.amqpAdmin.declareBinding(relacionaPreco);
    }
}
