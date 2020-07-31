package spring.boot._2.by.howtodoinjava._1;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class I18nController {
  @Autowired MessageSource messageSource;

  @GetMapping("/")
  // Locale - By default, AcceptHeaderLocaleResolver implementation is used, which utilizes the HTTP
  // request header's 'Accept-Language'
  public String index(Locale locale) {
    return messageSource.getMessage("error.notfound", null, locale);
  }
}
