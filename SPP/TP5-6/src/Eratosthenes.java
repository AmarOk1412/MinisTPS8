import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class Eratosthenes {
	
	public int Below = 100;

	//TODO Calculate time
	@Test
	public void sequentialCheck500kPrimeNumber() {
		Integer limit = new Integer(Below);
		PrimeNumberFactory pnf = new SequentialEratosthenesSieve();
		long begin = System.currentTimeMillis();
		ArrayList<Integer> primeNumber = pnf.getPrimes(limit);
		long end = System.currentTimeMillis();
		long duration = end - begin;
		System.out.println("Durée : " + duration);
		int numberPrimes = 0;
		
		try (BufferedReader br = new BufferedReader(new FileReader("primes-to-100k.txt")))
		{
			String line;

			while ((line = br.readLine()) != null) {
				Integer lineToPrime = new Integer(line);
				if(lineToPrime > limit) break;
				++numberPrimes;
				assertTrue(primeNumber.contains(lineToPrime));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		assertTrue(numberPrimes == primeNumber.size());
	}
	
	@Test
	public void parallelCheck500kPrimeNumber() {
		for(int limitThreads = 1; limitThreads <= 10; ++limitThreads)
		{
			Integer limit = new Integer(Below);
			PrimeNumberFactory pnf = new ParallelEratosthenesSieve(limitThreads);
			long begin = System.currentTimeMillis();
			ArrayList<Integer> primeNumber = pnf.getPrimes(limit);
			long end = System.currentTimeMillis();
			long duration = end - begin;
			System.out.println("Durée : " + duration);
			int numberPrimes = 0;
			
			try (BufferedReader br = new BufferedReader(new FileReader("primes-to-100k.txt")))
			{
				String line;
	
				while ((line = br.readLine()) != null) {
					Integer lineToPrime = new Integer(line);
					if(lineToPrime > limit) break;
					++numberPrimes;
					assertTrue(primeNumber.contains(lineToPrime));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			assertTrue(numberPrimes == primeNumber.size());
		}
	}
}
