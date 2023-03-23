public class SerialParallel {

    public static void main(String[] args) {
        long startS = System.nanoTime();
        SerialParallel spS = new SerialParallel();

        spS.primeSerial();

        long endS = System.nanoTime();
        double differenceS = (endS - startS) / 1000000.0;
        System.out.println("This took " + (int) differenceS +
        " milliseconds serially.\n\n");

        PrimeCount pc = new PrimeCount();
        Thread t1 = new Thread(() -> {
            for (int i = 1000000 ; i < 1250000 ; i++) {
                boolean primeCheck = true;
                for (int j = 2 ; j <= Math.sqrt(i) ; j++) {
                    if (i % j == 0) {
                        primeCheck = false;
                    }
                }
                if (primeCheck) {
                    pc.inc();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1250000 ; i < 1500000 ; i++) {
                boolean primeCheck = true;
                for (int j = 2 ; j <= Math.sqrt(i) ; j++) {
                    if (i % j == 0) { primeCheck = false; }
                }
                if (primeCheck) { pc.inc(); }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 1500000 ; i < 1750000 ; i++) {
                boolean primeCheck = true;
                for (int j = 2 ; j <= Math.sqrt(i) ; j++) {
                    if (i % j == 0) { primeCheck = false; }
                }
                if (primeCheck) { pc.inc(); }
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 1750000 ; i < 2000000 ; i++) {
                boolean primeCheck = true;
                for (int j = 2 ; j <= Math.sqrt(i) ; j++) {
                    if (i % j == 0) { primeCheck = false; }
                }
                if (primeCheck) { pc.inc(); }
            }
        });

        long startP = System.nanoTime();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch(Exception e) {
            System.out.println("An exception happened!");
        }
        long endP = System.nanoTime();
        double differenceP = (endP - startP)  / 1000000.0;

        System.out.println("There are " + pc.primeCount + " primes in " +
        (int) differenceP + " milliseconds as a parallel operation.");
    }

    public void primeSerial() {
        int primeInc = 0;
        int j = 0;
        for (int num = 1000000 ; num < 2000000 ; num++) {
            boolean primeCheck = true;
            for (int i = 2 ; i <= Math.sqrt(num) ; i++) {
                if (num % i == 0) { primeCheck = false; }
        }
            if (primeCheck && j < 10) {
                System.out.println(num);
                primeInc++;
                j++;
            }
            else if (primeCheck) { primeInc++; }
        }
        System.out.println("There are " + primeInc + " primes.");
    }
}


class PrimeCount {
    int primeCount;

    public synchronized void inc() {
        primeCount++;
    }
}

/*
OUTPUT:
1000003
1000033
1000037
1000039
1000081
1000099
1000117
1000121
1000133
1000151
There are 70435 primes.
This took 9163 milliseconds serially.


70435 primes in 4308 milliseconds as a parallel operation.

Process finished with exit code 0

 */
