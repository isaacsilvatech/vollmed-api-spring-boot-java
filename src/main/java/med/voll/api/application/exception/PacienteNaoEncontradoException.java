package med.voll.api.application.exception;

public class PacienteNaoEncontradoException extends RuntimeException {
    public PacienteNaoEncontradoException(String message) {
        super(message);
    }
}
