package com.bhupesh.datastructure;

import org.hamcrest.collection.IsEmptyCollection;

public class ProducerConsumer {

	Object lock = new Object();
	
	int[] capacity= new int[50];
	
	int size= 0;
	 
	boolean isfull(){
		if(size == 50){
			return true;
		}
		return false;
	}
	
    boolean empty(){
    	if(size == 0){
			return true;
		}
		return false;
	}
    
    public void producer(int i) throws InterruptedException{
    	
    	synchronized(lock){
    		while(isfull()){
    			lock.wait();
    		}
    		size++;
    		capacity[size-1] =i;
    		System.out.println("Producer = "+ i);
    		lock.notifyAll();
    	}
    	
    }
    
    public void consumer() throws InterruptedException{
    	
    	synchronized(lock){
    		while(empty()){
    			lock.wait();
    		}
    		size= size-1;
    		System.out.println("Consumer = " + capacity[size]);
    		capacity[size] =0;
    		lock.notifyAll();
    	}
    	
    }
}
