package practice;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class EvenThread extends Thread {
    private final Lock lock;
    private final Condition condition;
    private final Condition otherCondition;
    private final int limit;

    EvenThread(Lock lock, Condition condition, Condition otherCondition, int limit) {
        this.lock = lock;
        this.condition = condition;
        this.otherCondition = otherCondition;
        this.limit = limit;
    }

    public void run() {
        for (int i = 0; i < limit; i += 2)
        {
            lock.lock();
            try
            {
                System.out.println(i);
                otherCondition.signal();
                condition.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}

class OddThread extends Thread {
    private final Lock lock;
    private final Condition condition;
    private final Condition otherCondition;
    private final int limit;

    OddThread(Lock lock, Condition condition, Condition otherCondition, int limit) {
        this.lock = lock;
        this.condition = condition;
        this.otherCondition = otherCondition;
        this.limit = limit;
    }

    public void run() {
        for (int i = 1; i < limit; i += 2)
        {
            lock.lock();
            try {
                System.out.println(i);
                otherCondition.signal();
                condition.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }

    }
}

public class EvenOddPrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the limit:");
        int limit = scanner.nextInt();
        Lock lock = new ReentrantLock();
        Condition evenCondition = lock.newCondition();
        Condition oddCondition = lock.newCondition();
        EvenThread evenThread = new EvenThread(lock, evenCondition, oddCondition, limit);
        OddThread oddThread = new OddThread(lock, oddCondition, evenCondition, limit);
        evenThread.start();
        oddThread.start();
    }
}

//package practice;
//
//import java.util.Scanner;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//class EvenThread extends Thread {
//    private final Lock lock;
//    private final int limit;
//
//    EvenThread(Lock lock, int limit) {
//        this.lock = lock;
//        this.limit = limit;
//    }
//
//    public void run() {
//        for (int i = 0; i < limit; i++)
//        {
//            if (i % 2 == 0)
//            {
//                lock.lock();
//                try {
//                    System.out.println(i);
//                    // Additional synchronization logic would be required here
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }
//    }
//}
//
//class OddThread extends Thread {
//    private final Lock lock;
//    private final int limit;
//
//    OddThread(Lock lock, int limit)
//    {
//        this.lock = lock;
//        this.limit = limit;
//    }
//
//    public void run() {
//        for (int i = 0; i < limit; i++) {
//            if (i % 2 != 0) {
//                lock.lock();
//                try {
//                    System.out.println(i);
//                    // Additional synchronization logic would be required here
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }
//    }
//}
//
//public class EvenOddPrinter
//{
//    public static void main(String[] args)
//    {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the limit:");
//        int limit = scanner.nextInt();
//        Lock lock = new ReentrantLock();
//        EvenThread evenThread = new EvenThread(lock, limit);
//        OddThread oddThread = new OddThread(lock, limit);
//        evenThread.start();
//        oddThread.start();
//    }
//}

