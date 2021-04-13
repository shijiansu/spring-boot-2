package spring.boot._2.by.howtodoinjava._7;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncService {

	private static Logger log = LoggerFactory.getLogger(AsyncService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// It can use different ThreadPoolTaskExecutor
	@Async("asyncExecutor2")
	public CompletableFuture<EmployeeNames> getEmployeeName() throws InterruptedException 
	{
		log.info("getEmployeeName Starts");
		// Simulate application integration by API
		EmployeeNames names = restTemplate.getForObject("http://localhost:8080/names", EmployeeNames.class);

		log.info("names, {}", names);
		Thread.sleep(1000L);	// Simulate intentional delay
		log.info("names completed");
		return CompletableFuture.completedFuture(names);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeeAddresses> getEmployeeAddress() throws InterruptedException 
	{
		log.info("getEmployeeAddress Starts");
    // Simulate application integration by API
		EmployeeAddresses addresses = restTemplate.getForObject("http://localhost:8080/addresses", EmployeeAddresses.class);

		log.info("addresses, {}", addresses);
		Thread.sleep(1000L);	// Simulate intentional delay
		log.info("addresses completed");
		return CompletableFuture.completedFuture(addresses);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeePhone> getEmployeePhone() throws InterruptedException 
	{
		log.info("getEmployeePhone Starts");
    // Simulate application integration by API
		EmployeePhone phones = restTemplate.getForObject("http://localhost:8080/phones", EmployeePhone.class);

		log.info("phones, {}", phones);
		Thread.sleep(1000L);	// Simulate intentional delay
		log.info("phones completed");
		return CompletableFuture.completedFuture(phones);
	}

}
