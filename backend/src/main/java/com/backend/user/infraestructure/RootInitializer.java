package com.backend.user.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.backend.user.application.UserService;
import com.backend.user.domain.dto.UserDto;

@Component
public class RootInitializer {
    
    @Autowired
    private UserService userService;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        try {
            if (userService.findOneByUsername("root").isEmpty()) {
                userService.createadminUser();
                createMockUser("123", "JuanDev", "juandev", 1L);
                createMockUser("124", "JhonDev", "jhondev", 4L);
                createMockUser("125", "AlejoDev", "alejodev", 4L);
                System.out.println("Usuario root creado exitosamente");
            } else {
                System.out.println("Usuario root ya existe");
            }
        } catch (Exception e) {
            System.err.println("Error al crear usuario root: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createMockUser(String id, String userName, String password, Long userTypeId) {
        UserDto newUser = new UserDto();
        newUser.setId(id);
        newUser.setUsername(userName);
        newUser.setPassword(password);
        newUser.setRepeatedPassword(password);
        newUser.setUsertypeId(userTypeId);

        userService.save(newUser);
    }
}