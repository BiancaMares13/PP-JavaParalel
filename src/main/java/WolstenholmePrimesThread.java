import java.math.BigInteger;

public class WolstenholmePrimesThread extends Thread {
    private int idThread;
    private int a;
    private int b;



    public WolstenholmePrimesThread(int idThread, int a, int b) {
        this.idThread = idThread;
        this.a = a;
        this.b = b;
    }

    public void run() {
        int start=a;
            if(a==1){
                start=2;
            }

        for (int p = start; p * p <= b; p+=4) {
            if (Main.primes[p]) {
                for ( int i = p * 2; i<= b; i += p)

                    Main.primes[i] = false;
            }
        }


      Main.primes[1]=false;

        BigInteger numerator;
        BigInteger denominator;
        int lastIndexFromHarmonicSum=0;
        for (int i = a; i <= b; i += 4) {
            numerator = BigInteger.ONE;
            denominator = BigInteger.ONE;

            BigInteger numeratorPrev;


            if(i==a) {
                if (i > 1) {
                    for (int j = 2; j < i; j++) {
                        BigInteger currentNumber = BigInteger.valueOf(j);
                        BigInteger lcm = (denominator.multiply(currentNumber)).divide(denominator.gcd(currentNumber));
                        numeratorPrev = numerator;
                        numerator = numeratorPrev.multiply(lcm.divide(denominator)).add(lcm.divide(currentNumber));
                        denominator = lcm;
                    }


                }
                lastIndexFromHarmonicSum = i;

            }
            if (Main.primes[i]) {
                BigInteger prime = BigInteger.valueOf(i);
                for (int j = lastIndexFromHarmonicSum; j < i; j++) {
                BigInteger currentNumber = BigInteger.valueOf(j);

                BigInteger lcm = (denominator.multiply(currentNumber)).divide(denominator.gcd(currentNumber));
                numeratorPrev = numerator;

                    numerator = numeratorPrev.multiply(lcm.divide(denominator)).add(lcm.divide(currentNumber));

                denominator = lcm;


            }



                BigInteger mod = numerator.mod(prime.pow(3));
                if(i==16843){
                    System.out.println("hndkjdfhsj  bmod "+mod);
                    System.out.println(prime);
                    System.out.println(prime.pow(3));
                }
                if (mod.equals(BigInteger.ZERO)) {
                    System.out.println(i+"  by thread    "+idThread);
                    Main.results.get(idThread).add(BigInteger.valueOf(i));

                }
                lastIndexFromHarmonicSum=i;
        }

    }
}

}
