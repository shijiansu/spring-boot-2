package cn.timebusker;

import cn.timebusker.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private CalculateService calculateService;

    @RequestMapping("/add")
    public double add(double v1, double v2) {
        return calculateService.add(v1, v2);
    }

    @RequestMapping("/sub")
    public double sub(double v1, double v2) {
        return calculateService.sub(v1, v2);
    }

    @RequestMapping("/div")
    public double div(double v1, double v2) {
        return calculateService.div(v1, v2);
    }

    @RequestMapping("/mul")
    public double mul(double v1, double v2) {
        return calculateService.mul(v1, v2);
    }

    @RequestMapping("/setScale")
    public double setScale(double v, int scale) {
        return calculateService.setScale(v, scale);
    }

    @RequestMapping("/setScale2")
    public double setScale(double v) {
        return calculateService.setScale(v);
    }
}
