package com.globanttest.domain;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GeneratorId {

	public Long getRandomId(){
		long range = 1234567L;
		Random r = new Random(); 
		long x = nextLong(r,range);
		
		return x;
	}
	
	private long nextLong(Random rng, long n) {
		   // error checking and 2^x checking removed for simplicity.
		   long bits, val;
		   do {
		      bits = (rng.nextLong() << 1) >>> 1;
		      val = bits % n;
		   } while (bits-val+(n-1) < 0L);
		   return val;
		}
}
