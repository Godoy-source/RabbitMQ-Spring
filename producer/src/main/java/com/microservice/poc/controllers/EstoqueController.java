package com.microservice.poc.controllers;

import com.microservice.poc.services.RabbitmqService;
import constantes.RabbitmqConstantes;
import dto.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Vai ser exporta por uma API Rest
@RequestMapping(value = "estoque") // Mapeia rota
public class EstoqueController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    private ResponseEntity alteraEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        System.out.println(estoqueDTO.codigoProduto);
        this.rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_ESTOQUE, estoqueDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
