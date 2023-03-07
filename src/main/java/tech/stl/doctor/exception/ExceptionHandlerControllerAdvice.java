package tech.stl.doctor.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice  {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;

  }
     @ExceptionHandler(ResourceNotFoundException.class)
        @ResponseStatus(value = HttpStatus.NOT_FOUND)
        public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
                final HttpServletRequest request) {

            ExceptionResponse error = new ExceptionResponse();
            error.setErrorMessage(exception.getMessage());
            error.callerURL(request.getRequestURI());

            return error;
        }

        @ExceptionHandler(Exception.class)
        @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
        public @ResponseBody ExceptionResponse handleException(final Exception exception,
                final HttpServletRequest request) {

            ExceptionResponse error = new ExceptionResponse();
            error.setErrorMessage(exception.getMessage());
            error.callerURL(request.getRequestURI());

            return error;
        }
}