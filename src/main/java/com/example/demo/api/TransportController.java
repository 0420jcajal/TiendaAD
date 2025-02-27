package com.example.demo.api;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Transport;
import com.example.demo.repository.TransportRepository;;



@RestController
@RequestMapping("/api/v1/transports")
public class TransportController {
    private final TransportRepository transportRepository;

    public TransportController(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @GetMapping
    public List<Transport> obtenerTodos(){
        return transportRepository.findAll();
        
    }

    @PostMapping
    public Transport createTrasport(@RequestBody Transport transport){
        return transportRepository.save(transport);
    }

}
