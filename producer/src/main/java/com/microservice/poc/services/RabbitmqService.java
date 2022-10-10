package com.microservice.poc.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqService {

    @Autowired // Permite que o spring resolva e injete a classe, instanciando o objeto
    private RabbitTemplate rabbitTemplate;


    public void enviaMensagem(String nomeFila, Object mensagem) {
                                                // Route key, msg
        this.rabbitTemplate.convertAndSend(nomeFila, mensagem);
    }
}


