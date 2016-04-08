import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class Eratosthenes {

	@Test
	public void sequentialCheck100PrimeNumber() {
		Integer limit = new Integer(100);
		PrimeNumberFactory pnf = new SequentialEratosthenesSieve();
		ArrayList<Integer> primeNumber = pnf.getPrimes(limit);
		
		try (BufferedReader br = new BufferedReader(new FileReader("primes-to-100k.txt")))
		{
			String line;

			while ((line = br.readLine()) != null) {
				Integer lineToPrime = new Integer(line);
				if(lineToPrime > limit) break;
				assertTrue(primeNumber.contains(lineToPrime));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void parallelCheck100PrimeNumber() {
		Integer limit = new Integer(100);
		PrimeNumberFactory pnf = new ParallelEratosthenesSieve();
		ArrayList<Integer> primeNumber = pnf.getPrimes(limit);
		
		try (BufferedReader br = new BufferedReader(new FileReader("primes-to-100k.txt")))
		{
			String line;

			while ((line = br.readLine()) != null) {
				Integer lineToPrime = new Integer(line);
				if(lineToPrime > limit) break;
				assertTrue(primeNumber.contains(lineToPrime));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
