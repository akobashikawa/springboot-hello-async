package me.rulokoba.async;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@Autowired
	private final HelloService service;

	public HelloController(HelloService service) {
		this.service = service;
	}

	@RequestMapping("/")
	public @ResponseBody String home() {
		return "Hola Async";
	}
	
	@RequestMapping("/delay")
	public @ResponseBody String delay(@RequestParam(name = "time", defaultValue="1000") long time) throws InterruptedException {
//		String result = this.service.delay(time);
//		String result2 = this.service.delay(time);
//		return result + result2;
		
		ArrayList<String> results = new ArrayList<String>();
		int n = 30;
		
		for (int i=0; i<n; i++) {
			String result = this.service.delay(time);
			results.add(result);
		}
		
		String messages = "<ol>";
		for (int i=0; i<n; i++) {
			messages = messages + "<li>" + results.get(i) + "</li>";
		}
		messages = messages + "</ol>";
		
		return messages;
	}
	
	@RequestMapping("/adelay")
	public @ResponseBody String adelay(@RequestParam(name = "time", defaultValue="1000") long time) throws InterruptedException, ExecutionException {
//		Future<String> future = this.service.adelay(time);
//		Future<String> future2 = this.service.adelay(time);
//		Future<String> future3 = this.service.adelay(time);
//		return future.get() + future2.get() + future3.get();
		
//		// Impide el async
//		String message = this.service.adelay(time).get();
//		String message2 = this.service.adelay(time).get();
//		String message3 = this.service.adelay(time).get();
//		return message + message2 + message3;
	
		ArrayList<Future<String>> futures = new ArrayList<Future<String>>();
		
		int n = 16;
		
		for (int i=0; i<n; i++) {
			Future<String> future = this.service.adelay(time);
			futures.add(future);
		}
		
		String messages = "<ol>";
		for (int i=0; i<n; i++) {
			messages = messages + "<li>" + futures.get(i).get() + "</li>";
		}
		messages = messages + "</ol>";
		
		return messages;
	}
	
	@RequestMapping("/adelay2")
	public @ResponseBody String adelay2(@RequestParam(name = "time", defaultValue="1000") long time) throws InterruptedException, ExecutionException {
//		Future<String> future = this.service.adelay2(time);
//		Future<String> future2 = this.service.adelay2(time);
//		Future<String> future3 = this.service.adelay2(time);
//		return future.get() + future2.get() + future3.get();
		
		ArrayList<CompletableFuture<String>> futures = new ArrayList<CompletableFuture<String>>();
		
		int n = 16;
		
		for (int i=0; i<n; i++) {
			CompletableFuture<String> future = this.service.adelay2(time);
			futures.add(future);
		}
		
		String messages = "<ol>";
		for (int i=0; i<n; i++) {
			messages = messages + "<li>" + futures.get(i).get() + "</li>";
		}
		messages = messages + "</ol>";
		
		return messages;
	}
	
	@RequestMapping("/adelay2b")
	public @ResponseBody String adelay2b(@RequestParam(name = "time", defaultValue="1000") long time) throws InterruptedException, ExecutionException {
		LinkedList <CompletableFuture<String>> futures = new LinkedList<CompletableFuture<String>> ();
		
		
		int n = 16;
		int i = 0;
		
		String messages = "<ol>";
		
		do {
			if (i < n) {
				CompletableFuture<String> future = this.service.adelay2(time);
				futures.add(future);
			}
			i++;
			
			if (futures.element().isDone()) {
				messages = messages + "<li> (i: " + i + ")" + futures.remove().get() + "</li>";
			}
		} while (!futures.isEmpty());
		
		messages = messages + "</ol>";
		
		return messages;
	}
}
