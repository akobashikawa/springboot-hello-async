package me.rulokoba.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

	public String delay(long time) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		Thread.sleep(time);
		long endTime = System.currentTimeMillis();
		
		double random = Math.random();
		String message = "delay [" + random + "]: " + endTime + "-" + startTime + "=" + (endTime - startTime);
		
		return message;
	}
	
	@Async
	public Future<String> adelay(long time) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		Thread.sleep(time);
		long endTime = System.currentTimeMillis();
		
		double random = Math.random();
		String message = "adelay [" + random + "]: " + endTime + "-" + startTime + "=" + (endTime - startTime);
		
		return new AsyncResult<String>(message);
	}
	
	@Async
	public CompletableFuture<String> adelay2(long time) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		Thread.sleep(time);
		long endTime = System.currentTimeMillis();
		
		double random = Math.random();
		String message = "adelay2 [" + random + "]: " + endTime + "-" + startTime + "=" + (endTime - startTime);
		
		return CompletableFuture.completedFuture(message);
	}
}
