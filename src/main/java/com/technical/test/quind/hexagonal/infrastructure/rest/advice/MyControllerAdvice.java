package com.technical.test.quind.hexagonal.infrastructure.rest.advice;

import com.technical.test.quind.hexagonal.infrastructure.adapter.exepcion.AccountException;
import com.technical.test.quind.hexagonal.infrastructure.adapter.exepcion.ClientException;
import com.technical.test.quind.hexagonal.infrastructure.adapter.exepcion.ProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<String> handleEmptyInput(AccountException emptyInputException){
        return new ResponseEntity<String>(emptyInputException.getErrorMessage(), emptyInputException.getErrorCode());
    }
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<String> handleEmptyInput(ClientException emptyInputException){
        return new ResponseEntity<String>(emptyInputException.getErrorMessage(), emptyInputException.getErrorCode());
    }
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<String> handleEmptyInput(ProductException emptyInputException){
        return new ResponseEntity<String>(emptyInputException.getErrorMessage(), emptyInputException.getErrorCode());
    }
}
