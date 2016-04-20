import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class Eratosthenes {
	
	public int Below = 1000000;


	@Test
	public void sequentialCheck500kPrimeNumber() {
		Integer limit = new Integer(Below);
		PrimeNumberFactory pnf = new SequentialEratosthenesSieve();
		long begin = System.currentTimeMillis();
		//On récupère les nombres premiers
		ArrayList<Integer> primeNumber = pnf.getPrimes(limit);
		long end = System.currentTimeMillis();
		//Calcul de la durée
		long duration = end - begin;
		InfoLogger.log(Level.INFO, "duration={0}", new Object[]{duration});
		
		//On vérifie que les nombres premiers soient bien dans le tableau
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
	public void parallelCheck500kPrimeNumber() {
		//On change le nombre de threads
		for(int limitThreads = 1; limitThreads <= 10; ++limitThreads)
		{
			Integer limit = new Integer(Below);
			InfoLogger.log(Level.INFO, "k={0}", new Object[]{limitThreads});
			//On récupère les nombres premiers
			PrimeNumberFactory pnf = new ParallelEratosthenesSieve(limitThreads);
			long begin = System.currentTimeMillis();
			ArrayList<Integer> primeNumber = pnf.getPrimes(limit);
			long end = System.currentTimeMillis();
			//Calcul de la durée
			long duration = end - begin;
			InfoLogger.log(Level.INFO, "duration={0}", new Object[]{duration});

			
			//On vérifie que les nombres premiers soient bien dans le tableau
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
	
	@Test
	public void sequentialCheck100kPrimeNumber() {
		Integer limit = new Integer(100000);
		PrimeNumberFactory pnf = new SequentialEratosthenesSieve();
		long begin = System.currentTimeMillis();
		//On récupère les nombres premiers
		ArrayList<Integer> primeNumber = pnf.getPrimes(limit);
		long end = System.currentTimeMillis();
		//Calcul de la durée
		long duration = end - begin;
		InfoLogger.log(Level.INFO, "duration={0}", new Object[]{duration});
		int numberPrimes = 0;
		
		//On vérifie que les nombres premiers soient bien dans le tableau
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
		//On vérifie qu'on a le bon nombres d'entiers
		assertTrue(numberPrimes == primeNumber.size());
	}
	
	@Test
	public void parallelCheck100kPrimeNumber() {
		//On change le nombre de threads
		for(int limitThreads = 1; limitThreads <= 10; ++limitThreads)
		{
			Integer limit = new Integer(100000);
			InfoLogger.log(Level.INFO, "k={0}", new Object[]{limitThreads});
			//On récupère les nombres premiers
			PrimeNumberFactory pnf = new ParallelEratosthenesSieve(limitThreads);
			long begin = System.currentTimeMillis();
			ArrayList<Integer> primeNumber = pnf.getPrimes(limit);
			long end = System.currentTimeMillis();
			//Calcul de la durée
			long duration = end - begin;
			//Calcul de la durée
			InfoLogger.log(Level.INFO, "duration={0}", new Object[]{duration});
			int numberPrimes = 0;

			//On vérifie que les nombres premiers soient bien dans le tableau
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
			//On vérifie qu'on a le bon nombre d'entiers
			assertTrue(numberPrimes == primeNumber.size());
		}
	}
}
