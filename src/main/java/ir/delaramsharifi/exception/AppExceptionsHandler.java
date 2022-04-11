package ir.delaramsharifi.exception;

import ir.delaramsharifi.exception.controller.BadRequestException;
import ir.delaramsharifi.exception.controller.InternalServerErrorException;
import ir.delaramsharifi.exception.controller.NotAcceptableException;
import ir.delaramsharifi.exception.controller.NotFoundException;
import ir.delaramsharifi.model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionsHandler {

    String dateTime = LocalDateTime.now().toString();


    @ExceptionHandler(value = {InternalServerErrorException.class})
    public ResponseEntity<?> handleInternalServerErrorException(InternalServerErrorException exception, WebRequest request) {

        return new ResponseEntity<>(
                new ErrorMessage(dateTime, HttpStatus.INTERNAL_SERVER_ERROR.toString()
                        , exception.getMessage())
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception, WebRequest request) {

        return new ResponseEntity<>(
                new ErrorMessage(dateTime, HttpStatus.NOT_FOUND.toString()
                        , exception.getMessage())
                , HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointServiceException(NullPointerException exception,
                                                                  WebRequest request) {
        ErrorMessage handleNullPointServiceException = new ErrorMessage(dateTime,
                HttpStatus.NOT_FOUND.toString(), exception.getMessage());
        return new ResponseEntity<>(handleNullPointServiceException, new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<?> handleBadRequestException(BadRequestException exception, WebRequest request) {

        return new ResponseEntity<>(
                new ErrorMessage(dateTime, HttpStatus.BAD_REQUEST.toString()
                        , exception.getMessage())
                , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = {NotAcceptableException.class})
    public ResponseEntity<?> handleNotAcceptableException(NotAcceptableException exception, WebRequest request) {

        return new ResponseEntity<>(
                new ErrorMessage(dateTime, HttpStatus.NOT_ACCEPTABLE.toString()
                        , exception.getMessage())
                , HttpStatus.NOT_ACCEPTABLE
        );
    }
}
