package ua.lviv.iot.lab6.exception;

public class GuideNotFoundException extends RuntimeException {
    public GuideNotFoundException(Integer id) {
        super("Could not find 'client' with id=" + id);
    }
}
