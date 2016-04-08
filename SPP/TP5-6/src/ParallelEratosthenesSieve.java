import java.util.ArrayList;

public class ParallelEratosthenesSieve implements PrimeNumberFactory {

	public static ArrayList<Boolean> isPrime = null;
	
	@Override
	public ArrayList<Integer> getPrimes(Integer limit) {
		ArrayList<Integer> primes = new ArrayList<>();
		isPrime = new ArrayList<>();
		for(int i = 0; i <= limit-2; ++i)
		{
			isPrime.add(true);
		}
		
		return primes;
	}

}
