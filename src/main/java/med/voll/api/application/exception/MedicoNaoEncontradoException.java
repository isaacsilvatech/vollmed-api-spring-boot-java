package med.voll.api.application.exception;

public class MedicoNaoEncontradoException extends RuntimeException {
    public MedicoNaoEncontradoException(String message) {
        super(message);
    }
}
