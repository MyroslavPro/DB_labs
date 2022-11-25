package ua.lviv.iot.lab6.exception;

public class SightNotFoundException extends RuntimeException {
    public SightNotFoundException(Integer id) {
        super("Could not find 'sight' with id=" + id);
    }
}
