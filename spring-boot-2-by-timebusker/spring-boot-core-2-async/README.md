
## Spring中的@Async
在Spring中，基于@Async标注的方法，称之为异步方法；这些方法将在执行的时候，将会在独立的线程中被执行，调用者无需等待它的完成，即可继续其他的操作。

- #### 启用@Async 
基于注解启用方式
```java
@Configuration  
@EnableAsync  
public class SpringAsyncConfig { ... } 
```	 

- #### 基于XML配置启用方式
```xml
<task:executor id="executor" pool-size="5" />
<task:annotation-driven executor="executor"/>
```	 	 

- #### 基于@Async无返回值调用
```java
	/**
	 * 仅使用异步注解的方式实现异步方法
	 * 
	 * @return
	 */
	@Async
	public void subByonly() throws Exception {
		long start = System.currentTimeMillis();
		long sum = 0;
		Thread.sleep(DoTime);
		long end = System.currentTimeMillis();
		sum = end - start;
		logger.info("\t 完成任务二   ");
		logger.info("注解任务执行的时间是： " + sum + "（毫秒）");
	}
```	
	 
- #### 基于@Async返回值的调用
```java
	/**
	 * 异步任务 只需要在所需实现异步的方法上加上@Async注解， 并通过Future<T>来接受异步方法的处理结果
	 * 通过@Async注解表明该方法是个异步方法，如果注解在类级别，则表明该类所有的方法都是异步方法
	 * @return
	 */
	@Async
	public Future<Long> subByAsync() throws Exception {
		long start = System.currentTimeMillis();
		long sum = 0;
		Thread.sleep(DoTime);
		long end = System.currentTimeMillis();
		sum = end - start;
		logger.info("\t 完成任务一");
		return new AsyncResult<>(sum);
	}
```		 
***返回的数据类型为Future类型，实为一个接口，具体的结果类型为AsyncResult***	 
```java
// 调用异步方法
Future<Long> task = arithmeticService.subByAsync();
// 接受异步方法结果
while (true) {
	if (task.isDone()) {
		long async = task.get();
		logger.info("异步任务执行的时间是：" + async + "（毫秒）");
		break;
	}
}
```	 

- #### 异步方法执行效果图
![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-5-Async/async-test-res.png?raw=true)

---------------------------
---------------------------

## 优化异步调用
    在上面，我使用了最简单spring async实现方式，同时在测试***基于@Async返回值的调用***中，
    使用缺省的TaskExecutor，出现了提示：
```text
No TaskExecutor bean found for async processing
```
### 分析
    在spring中，通过任务执行器，也就是TaskExecutor来实现多线程和并发编程。

    使用ThreadPoolTaskExecutor可实现一个基于线程池的TaskExecutor。 
    而实际开发中任务一般是非阻碍的，也就是非异步的，所以我们要在配置类中通过@EnableAsync开启对异步任务的支持，
    并通过在实际执行的Bean的方法中使用@Async注解来声明其是一个异步任务。	 
	 
### 配置任务执行器
```java
/**
 * 通过重写getAsyncExecutor方法，制定默认的任务执行由该方法产生
 * 
 * 配置类实现AsyncConfigurer接口并重写getAsyncExcutor方法，并返回一个ThreadPoolTaskExevutor
 * 这样我们就获得了一个基于线程池的TaskExecutor
 */
@Override
public Executor getAsyncExecutor() {
	ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	taskExecutor.setCorePoolSize(CORE_POOL_SIZE);// 线程池维护线程的最少数量
	taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);// 线程池维护线程的最大数量
	taskExecutor.setQueueCapacity(QUEUE_CAPACITY);// 线程池所使用的缓冲队列
	taskExecutor.initialize();
	return taskExecutor;
}
```	 

### 异步与同步
     同步就是整个处理过程顺序执行，当各个过程都执行完毕，并返回结果。 
     异步调用则是只是发送了调用的指令，调用者无需等待被调用的方法完全执行完毕；而是继续执行下面的流程。
## 推荐文章
   - [Java多线程实现异步调用](http://blog.csdn.net/lovesomnus/article/details/50838774)
   - [Spring中@Async用法总结](http://blog.csdn.net/blueheart20/article/details/44648667)
   - [线程池ThreadPoolExecutor参数设置](http://blog.csdn.net/zhouhl_cn/article/details/7392607)
