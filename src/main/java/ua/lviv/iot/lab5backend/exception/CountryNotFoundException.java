package ua.lviv.iot.lab5backend.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Integer id) {
        super("Could not find 'country' with id=" + id);
    }
}
