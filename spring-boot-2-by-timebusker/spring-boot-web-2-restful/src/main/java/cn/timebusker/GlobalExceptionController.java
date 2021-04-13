package cn.timebusker;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

  public static final String DEFAULT_ERROR_VIEW = "error";

  @ExceptionHandler(value = ArithmeticException.class)
  @ResponseBody
  public ErrorMassage<Object> ErrorHandler(HttpServletRequest req, ArithmeticException e)
      throws Exception {
    ErrorMassage<Object> error = new ErrorMassage<>();
    error.setCode(405);
    error.setMessage("java.lang.ArithmeticException: / by zero");
    error.setUrl(req.getRequestURL().toString());
    error.setData(e);
    return error;
  }

  @ExceptionHandler(value = Exception.class)
  public ModelAndView ErrorHandler(HttpServletRequest req, Exception e) {
    ModelAndView mav = new ModelAndView();
    ErrorMassage<Exception> error = new ErrorMassage<>();
    error.setCode(400);
    error.setMessage("System error");
    error.setUrl(req.getRequestURI());
    error.setData(e);
    mav.addObject(error);
    mav.setViewName(DEFAULT_ERROR_VIEW);
    return mav;
  }
}
