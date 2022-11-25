package ua.lviv.iot.lab6.exception;

public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(Integer id) {
        super("Could not find 'trip' with id=" + id);
    }
}
