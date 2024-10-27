package com.backend.user.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.backend.user.application.UserService;

@Component
public class RootInitializer {
    
    @Autowired
    private UserService userService;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        try {
            if (userService.findOneByUsername("root").isEmpty()) {
                userService.createadminUser();
                System.out.println("Usuario root creado exitosamente");
            } else {
                System.out.println("Usuario root ya existe");
            }
        } catch (Exception e) {
            System.err.println("Error al crear usuario root: " + e.getMessage());
            e.printStackTrace();
        }
    }
}