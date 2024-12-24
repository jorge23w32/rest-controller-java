package med.voll.api.infra.error;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.infra.exception.ValidacionException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)//Se le da la excepcion a tratar
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)//Se le da la excepcion a tratar
    public ResponseEntity error400(MethodArgumentNotValidException exception){
        var errores = exception.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(ValidacionException.class)//Se le da la excepcion a tratar
    public ResponseEntity errorValidacion(ValidacionException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
