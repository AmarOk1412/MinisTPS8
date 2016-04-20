import java.util.ArrayList;

public class SequentialEratosthenesSieve implements PrimeNumberFactory {

	@Override
	public ArrayList<Integer> getPrimes(Integer limit) {
		//On récupère le tableau des booléens
		ArrayList<Integer> primes = new ArrayList<>();
		ArrayList<Boolean> isPrime = eratosthenesSieve(limit);
		//On le transforme en tableau d'entiers
		for(int i = 0; i < isPrime.size(); ++i)
		{
			if(isPrime.get(i))
			{
				primes.add(new Integer(2+i));
			}
		}
		return primes;
	}
	
	public ArrayList<Boolean> eratosthenesSieve(Integer limit) {
		//Initialisation du tableau de booléen
		ArrayList<Boolean> isPrime = new ArrayList<>();
		for(int i = 0; i <= limit-2; ++i)
		{
			isPrime.add(true);
		}
		
		int i = 2;
		while(Math.pow(i, 2) <= limit)
		{
			if(isPrime.get(i-2))
			{
				int iPow2 = (int) Math.pow(i, 2);
				int step = (i==2) ? i : 2*i; //Pour éviter de faire les nombres paires
				for(int j = iPow2; j <= limit; j += step)
				{
					 isPrime.set(j-2, false);
				}
			}
			i+=1;
		}
		
		return isPrime;
	}

}
