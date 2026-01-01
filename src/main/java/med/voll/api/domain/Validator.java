package med.voll.api.domain;

public interface Validator<T> {

    void validate(T t) throws ValidationException;
}
