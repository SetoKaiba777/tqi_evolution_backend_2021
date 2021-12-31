package com.kaibacorp.testetqi.api.exceptionhandler;

import com.kaibacorp.testetqi.domain.exception.DontFoundException;
import com.kaibacorp.testetqi.domain.exception.BusinessException;
import com.kaibacorp.testetqi.domain.exception.LoginException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleServiceExcpetion(BusinessException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        Problema problema = conjuntoDeProblemas(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleServiceExcpetion(LoginException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        Problema problema = conjuntoDeProblemas(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DontFoundException.class)
    public ResponseEntity<Object> handleDontFoundExcpetion(DontFoundException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        Problema problema = conjuntoDeProblemas(ex.getMessage(), status);
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request){

        var campos = new ArrayList<Problema.Campo>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();

            campos.add(new Problema.Campo(nome, msg));
        }
        Problema problema = conjuntoDeProblemas("Um ou mais campos não foram preenchidos corretamente",status);
        problema.setCampos(campos);
        return super.handleExceptionInternal(ex,problema, headers,status,request);
    }

    private Problema conjuntoDeProblemas(String msg, HttpStatus status){
        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo(msg);
        problema.setDataHora(OffsetDateTime.now());
        return problema;
    }

}
