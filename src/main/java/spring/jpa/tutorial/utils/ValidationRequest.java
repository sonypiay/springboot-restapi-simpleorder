package spring.jpa.tutorial.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationRequest {

    @Autowired
    private Validator validator;

    public void validate(Object request) {
        Set<ConstraintViolation<Object>> constraintViolation = validator.validate(request);

        if(!constraintViolation.isEmpty()) {
            throw new ConstraintViolationException(constraintViolation);
        }
    }
}
