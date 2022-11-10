package ua.lviv.iot.lab5backend.exception;

public class GuideNotFoundException extends RuntimeException {
    public GuideNotFoundException(Integer id) {
        super("Could not find 'client' with id=" + id);
    }
}
