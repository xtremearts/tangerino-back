package br.com.tangerino.tangerino.configurations;

import br.com.tangerino.tangerino.model.dtos.ResponseErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleErrorBusinessException(BusinessException e) {
        log.error(e.getMessage());
        return new ResponseEntity<Object>(
                new ResponseErrorDTO(e.getMessage(), e.getCode()),
                HttpStatus.FORBIDDEN);
    }

}
