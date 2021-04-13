package spring.boot._2.by.howtodoinjava._4;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class ApplicationExceptionHandler {

  // catch "ServletRequestBindingException" exception
  @org.springframework.web.bind.annotation.ExceptionHandler(ServletRequestBindingException.class)
  public final ResponseEntity<Object> handleHeaderException(Exception ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Bad Request", details);
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }

  // catch all teh rest of exceptions
  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Server Error", details);
    return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  class ErrorResponse {

    public ErrorResponse(String message, List<String> details) {

      this.message = message;
      this.details = details;
    }

    //General error message about nature of error
    private String message;

    //Specific errors in API request processing
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
}

