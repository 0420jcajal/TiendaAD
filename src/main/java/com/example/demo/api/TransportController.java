package com.example.demo.api;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Transport;
import com.example.demo.repository.TransportRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/v1/transports")
public class TransportController {
    private final TransportRepository transportRepository;

    private static final Logger logger= LoggerFactory.getLogger(TransportController.class);


    public TransportController(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @GetMapping
    public List<Transport> obtenerTodos(){
        try {
            return transportRepository.findAll();
        } catch (Exception e) {
            logger.error("Error al obtener la lista de transportes. Excepcion: {}", e);
            return null; 
        }
 
        
    }

    @PostMapping
    public Transport createTrasport(@RequestBody Transport transport){
        try {
            return transportRepository.save(transport)
        } catch (Exception e) {
            logger.error("Error al crear el transporte. Excepcion: {}", e);
            return null; 
        }
        return transportRepository.save(transport);
    }

}
