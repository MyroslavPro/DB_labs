package ua.lviv.iot.lab6.exception;

public class LanguageNotFoundException extends RuntimeException {
    public LanguageNotFoundException(Integer id) {
        super("Could not find 'language' with id=" + id);
    }
}
