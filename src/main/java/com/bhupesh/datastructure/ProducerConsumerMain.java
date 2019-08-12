package com.bhupesh.datastructure;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerMain {
	static Integer iii = new Integer(0);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ProducerConsumer pc = new ProducerConsumer();
		ExecutorService e  = Executors.newFixedThreadPool(200);
		
		Callable producer = () ->{
			
			
			for(int i=0; i<50;i ++){
				iii = iii.sum(iii, 1);
				pc.producer(iii);
			}
			return "produced";
		};
		
		
		Callable consume = () ->{
			
			for(int i=0; i<50;i ++){
				pc.consumer();
			}
			return "consumer";
		};
		
		
		try {
			for(int j=0 ; j<100; j++){
				e.submit(producer);
				e.submit(consume);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			e.shutdown();
		}
		
		
	}

}
