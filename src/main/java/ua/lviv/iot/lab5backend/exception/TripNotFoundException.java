package ua.lviv.iot.lab5backend.exception;

public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(Integer id) {
        super("Could not find 'trip' with id=" + id);
    }
}
