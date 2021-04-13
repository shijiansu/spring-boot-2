
```shell script
mvn clean spring-boot:run

http://localhost:8080/conf
http://localhost:8080/dubbo
http://localhost:8080/hello
```

## 实现多环境属性配置、自定义属性加载、快速搭建Spring Boot web项目

- spring-boot快速入门
  - 创建HelloController.java,显示输出web 输出 Hello spring-boot-1-QuickStart
- 属性配置文件详解
  - 多环境属性文件配置: Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式，其中{profile}对应你的环境标识。
  - 自定义属性加载: 需要使用@Component注解将类注册到容器内就可以方便使用
