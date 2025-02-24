package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.models.Pedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.requests.PedidoCreationRequest;

public class PedidoService {

    private static final Logger logger= LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;
    
}
