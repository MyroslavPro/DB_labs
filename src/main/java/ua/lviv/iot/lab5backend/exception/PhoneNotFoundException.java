package ua.lviv.iot.lab5backend.exception;

public class PhoneNotFoundException extends RuntimeException {
    public PhoneNotFoundException(Integer id) {
        super("Could not find 'phone' with id=" + id);
    }
}
