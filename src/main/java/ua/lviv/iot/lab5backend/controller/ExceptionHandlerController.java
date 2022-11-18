package ua.lviv.iot.lab5backend.controller;

import  ua.lviv.iot.lab5backend.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String countryNotFoundHandler(CountryNotFoundException ex) {
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String locationNotFoundHandler(LocationNotFoundException ex) {
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(LanguageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String languageNotFoundHandler(LanguageNotFoundException ex) {
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(PhoneNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String phoneNotFoundHandler(PhoneNotFoundException ex) {
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(TourNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String tourNotFoundHandler(TourNotFoundException ex) {
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String tripNotFoundHandler(TripNotFoundException ex) {
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientNotFoundHandler(ClientNotFoundException ex) {
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(GuideNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String guideNotFoundHandler(GuideNotFoundException ex) {
        return ex.getMessage();
    }

//    @ResponseBody
//    @ExceptionHandler(PersonHasBookException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    String personHasBookHandler(PersonHasBookException ex) {
//        return ex.getMessage();
//    }
}
