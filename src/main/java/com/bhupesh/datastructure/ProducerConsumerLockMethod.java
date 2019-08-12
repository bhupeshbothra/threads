package com.bhupesh.datastructure;

import java.math.BigInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLockMethod {


	Lock lock = new ReentrantLock();
	Condition full = lock.newCondition();
	Condition empty = lock.newCondition();

	int[] capacity = new int[50];

	int size = 0;

	boolean isfull() {
		if (size >= 50) {
			return true;
		}
		return false;
	}

	boolean empty() {
		if (size <= 0) {
			return true;
		}
		return false;
	}

	public void producer(int i) throws InterruptedException {

		try {
			lock.lock();
			
			while (isfull()) {
				 System.out.println(Thread.currentThread().getName());
     			full.await();
				System.out.println("Full Wait"+ i);
			}
			size++;
			capacity[size - 1] = i;
			System.out.println("Producer = "+ i);
			empty.signal();
		} finally {
			lock.unlock();
		}

	}

	public void consumer() throws InterruptedException {
	
		
		try {
			lock.lock();
			
			while (empty()) {
				empty.await();
				 System.out.println(Thread.currentThread().getName());
				System.out.println("empty Wait");
			}
			size--;
			System.out.println("Consumer = "+ capacity[size - 1]);
			capacity[size - 1] = 0;
			full.signalAll();
		} finally {
			lock.unlock();
		}

	}
}
