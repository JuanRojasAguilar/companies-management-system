package com.backend.servicereagent.domain;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.backend.servicereagent.domain.entity.ServiceReagent;

@Component
public class ServiceReagentValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ServiceReagent.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    ServiceReagent serviceReagent = (ServiceReagent) target;

    if (serviceReagent.getStock() < serviceReagent.getStockMin()) {
        errors.rejectValue("stock", null, "El stock no puede ser menor que el stock mínimo.");
    }
    if (serviceReagent.getStock() > serviceReagent.getStockMax()) {
        errors.rejectValue("stock", null, "El stock no puede ser mayor que el stock máximo.");
    }
}
}
