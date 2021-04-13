package spring.boot._2.by.howtodoinjava._5;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getMessage());
    ErrorResponse error = new ErrorResponse("Server Error", details);
    return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(RecordNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundException(
      RecordNotFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Record Not Found", details);
    return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }

  // ResponseEntityExceptionHandler has common exception scenario
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> details = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ErrorResponse error = new ErrorResponse("Validation Failed", details);
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }

  class ErrorResponse {
    public ErrorResponse(String message, List<String> details) {

      this.message = message;
      this.details = details;
    }

    // General error message about nature of error
    private String message;

    // Specific errors in API request processing
    private List<String> details;

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public List<String> getDetails() {
      return details;
    }

    public void setDetails(List<String> details) {
      this.details = details;
    }
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  static
  class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String exception) {
      super(exception);
    }
  }
}
