package com.example.consumidor.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import constantes.RabbitmqConstantes;
import dto.EstoqueDTO;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {

    // Marca um metodo para ser um ouvinte de mensagens de uma fila
    @RabbitListener(queues = RabbitmqConstantes.FILA_ESTOQUE)
    private void consumidor(EstoqueDTO estoqueDTO) {
        System.out.println(estoqueDTO.codigoProduto);
        System.out.println(estoqueDTO.quantidade);
    }
}
