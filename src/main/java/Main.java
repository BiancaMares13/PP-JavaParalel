import java.math.BigInteger;
import java.util.ArrayList;

public class Main {


    public static int n=2124680 ;
    private static int numberOfThreads=4;
    public static ArrayList<ArrayList<BigInteger>> results=new ArrayList<ArrayList<BigInteger>>();
    public static boolean primes[]=new boolean[n+1];



    public static void main(String[] args) {
        for(int i=0;i<numberOfThreads;i++){
            results.add(new ArrayList<BigInteger>());
        }


        for(int i=0;i<n;i++)
            primes[i] = true;

        long startTime = System.currentTimeMillis();

        Thread t0=new WolstenholmePrimesThread(0, 1,n);
        Thread t1=new WolstenholmePrimesThread(1, 2,n);
        Thread t2=new WolstenholmePrimesThread(2, 3,n);
        Thread t3=new WolstenholmePrimesThread(3, 4,n);

        t1.start();
        t2.start();
        t3.start();
        t0.start();

        try {
            t0.join();
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time        " +elapsedTime);

    }

}
