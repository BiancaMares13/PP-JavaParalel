import java.math.BigInteger;
import java.util.ArrayList;

public class Main {


    public static int n=16848 ;
    private static int numberOfThreads=4;
    public static ArrayList<ArrayList<BigInteger>> results=new ArrayList<ArrayList<BigInteger>>();
    public static boolean primes[]=new boolean[n+1];



    public static void main(String[] args) {
        for(int i=0;i<numberOfThreads;i++){
            results.add(new ArrayList<BigInteger>());
        }

        long startTime = System.currentTimeMillis();
        for(int i=0;i<n;i++)
            primes[i] = true;



        Thread t0=new WolstenholmePrimesThread(0, 7,n);
        Thread t1=new WolstenholmePrimesThread(1, 8,n);
        Thread t2=new WolstenholmePrimesThread(2, 9,n);
        Thread t3=new WolstenholmePrimesThread(3, 10,n);

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
