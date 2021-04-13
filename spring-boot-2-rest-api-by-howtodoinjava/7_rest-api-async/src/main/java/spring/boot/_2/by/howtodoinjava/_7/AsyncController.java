package spring.boot._2.by.howtodoinjava._7;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

  private static Logger log = LoggerFactory.getLogger(AsyncController.class);

  @Autowired private AsyncService service;

  @GetMapping(value = "/asynch")
  public void aynch() throws InterruptedException, ExecutionException {
    log.info("asynch Start");

    CompletableFuture<EmployeeAddresses> employeeAddress = service.getEmployeeAddress();
    CompletableFuture<EmployeeNames> employeeName = service.getEmployeeName();
    CompletableFuture<EmployeePhone> employeePhone = service.getEmployeePhone();

    // Wait until they are all done
    CompletableFuture.allOf(employeeAddress, employeeName, employeePhone).join();

    log.info("EmployeeAddress--> " + employeeAddress.get());
    log.info("EmployeeName--> " + employeeName.get());
    log.info("EmployeePhone--> " + employeePhone.get());
  }
}
