package io.explore.rxJava;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class HelloRxJava {

	public static void main(String[] args) throws InterruptedException {
		//helloObservable("RxJava", "Java8", ".NET", "PHP","Python","R");
		//observableComplete("Dhaka", "Delhi", "Sydney","Madison","Florida","Milwaukee");
		//rangeObservable();
		timeSeries();
	}

	private static void helloObservable(String... names) {
		Observable<String> namesObservable = Observable.fromArray(names);
		namesObservable
		.subscribe(name -> {
			System.out.println("Hello " + name);
		});
	}

	private static void observableComplete(String... names) {
		Observable<String> o = Observable.fromArray(names);
		o
		.subscribe(item -> {
			System.out.println("Hello, " + item);
		}, 
				item -> {
			System.out.println("Error");
		}, 
				() -> {
			System.out.println("Completed.");
		});
	}
	
	private static void rangeObservable() {
		Observable<Integer> range =  Observable.range(1,10);
		range.subscribe(System.out::print);
	}
	
	private static void timeSeries() throws InterruptedException {
		Observable<Long> t1 = Observable.interval(1L, TimeUnit.SECONDS);
		t1.subscribe(item ->{
			System.out.println(Thread.currentThread().getName() +" "+ Thread.currentThread().isDaemon());
		});
		
		Observable<Long> t2 = Observable.interval(2L, TimeUnit.SECONDS);
		t2.subscribe(item ->{
			System.out.println(Thread.currentThread().getName() +" "+ Thread.currentThread().isDaemon());
		});
		
		Observable<Long> t3 = Observable.interval(3L, TimeUnit.SECONDS);
		t3.subscribe(item ->{
			System.out.println(Thread.currentThread().getName() +" "+ Thread.currentThread().isDaemon());
		});
		
		System.out.println(Thread.currentThread().getName() +" "+ Thread.currentThread().isDaemon());
		Thread.sleep(10000);
		System.out.println(Thread.currentThread().getName() +" "+ Thread.currentThread().isDaemon());
	}
}
