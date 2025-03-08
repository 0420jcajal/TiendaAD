package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.requests.UserCreationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository= userRepository;
    }
    public User createUser(UserCreationRequest userCreationRequest){
        try {
            return userRepository.save(mapToUser(userCreationRequest));
        } catch (Exception e) {
            logger.error("Error al crear el Usuario. Exception: {}", e);
            return null;
        }
        
    }
    public void removeUser(Long id){
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error al borrar el usuario con id {}. Exception: {}", id, e);
        }
        
    }
    public Optional<User> getUser(final Long id){
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error al recuperar el usuario con id {}. Exception: {}", id, e);
            return null;
        }
        
    }
    public List<User> getAllUser(){
        try{
            return userRepository.findAll();
        } catch( Exception e){
            logger.error("Error listando los Usuarios {}", e);
            return new ArrayList<>();
        }
    }

    public User updateUser(Long id, User infoActualizar){
        try {
            User user= userRepository.findById(id)
                    .orElseThrow(() -> {
                        RuntimeException exception = new RuntimeException("Usuario no encontrado");
                        logger.error("Error al encontrar el usuario con ID {}. Excepcion: {}", id, exception.getMessage(), exception);
                        return exception;
                    });
            user.setNombre(infoActualizar.getNombre());
            user.setContrasena(infoActualizar.getContrasena());
            user.setEdad(infoActualizar.getEdad());
            user.setAdminstrador(infoActualizar.isAdminstrador());
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error al actualizar el usuario con id {}. Exception: {}", id, e);
            return null;
        }
    }




    public User mapToUser(UserCreationRequest userCreationRequest){
        User user= new User();
        user.setNombre(userCreationRequest.nombre());
        user.setContrasena(userCreationRequest.contrasena());
        user.setEdad(userCreationRequest.edad());
        user.setAdminstrador(userCreationRequest.administrador());
        return user;
    }
}
