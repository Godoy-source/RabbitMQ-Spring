package com.microservice.poc.controllers;

import com.microservice.poc.services.RabbitmqService;
import constantes.RabbitmqConstantes;
import dto.PrecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Vai ser exporta por uma API Rest
@RequestMapping(value = "preco") // Mapeia rota
public class PrecoController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    private ResponseEntity alteraPreco(@RequestBody PrecoDTO precoDTO) {
        System.out.println(precoDTO.preco);
        this.rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_PRECO, precoDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
