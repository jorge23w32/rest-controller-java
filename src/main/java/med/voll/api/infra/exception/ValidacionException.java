package med.voll.api.infra.exception;

public class ValidacionException extends RuntimeException{
    public  ValidacionException(String mensaje){
        super(mensaje);
    }
}
