import java.math.BigInteger;

public class WolstenholmePrimesThread extends Thread {
    private int idThread;
    private int a;
    private int b;



    public WolstenholmePrimesThread(int idThread, int a, int b) {
        this.idThread = idThread;
        this.a = a;
        this.b = b;
        System.out.println("Interval for thread "+ idThread +"   "+a+ "   "+ b );
    }

    public void run() {
        System.out.println("Thread"+idThread+" started execution");

        for (int p = a; p * p <= b; p+=4) {
            if (Main.primes[p]) {

                for (int i = p * 2; i <= b; i += p)

                    Main.primes[i] = false;
            }
        }


        BigInteger numerator;
        BigInteger denominator;
        for (int i = a; i <= b; i += 4) {
            if (Main.primes[i]) {
                numerator = BigInteger.ONE;
                denominator = BigInteger.ONE;

                BigInteger numeratorPrev;
                BigInteger prime = BigInteger.valueOf(i);
                for (int j = 2; j < i; j++) {
                BigInteger currentNumber = BigInteger.valueOf(j);
                BigInteger lcm = (denominator.multiply(currentNumber)).divide(denominator.gcd(currentNumber));
                numeratorPrev = numerator;
                numerator = numeratorPrev.multiply(lcm.divide(denominator)).add(lcm.divide(currentNumber));
                denominator = lcm;

            }
                BigInteger mod = numerator.mod(prime.pow(3));

                if (mod.equals(BigInteger.ZERO)) {

                    Main.results.get(idThread).add(BigInteger.valueOf(i));

                }
        }

    }

        System.out.println("Thread"+idThread+" finished execution");
}

}
