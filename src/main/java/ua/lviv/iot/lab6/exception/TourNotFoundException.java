package ua.lviv.iot.lab6.exception;

public class TourNotFoundException extends RuntimeException {
    public TourNotFoundException(Integer id) {
        super("Could not find 'tour' with id=" + id);
    }
}
