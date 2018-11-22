package ch09_multithreading;

/**
 * Created by sofia on 2/20/17.
 */
public class MultiThreading {

    public static void main(String[] args) {

        /******************************************************
         * extending Thread and implementing Runnable
         ******************************************************/
        System.out.println("=== Extending Thread Class and Implementing Runnable Interface ===");

        MyThread thread = new MyThread();
        thread.start();

        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();

        try {
            int iterations = 3;
            for (int i = 0; i < iterations; i++) {
                System.out.println("From main process");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        System.out.println();


        /******************************************************
         * interrupting thread
         ******************************************************/
        System.out.println("=== Interrupting Thread ===");

        MyThread newThread = new MyThread();
        newThread.start();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        newThread.interrupt();

        System.out.println("Called interrupt()");
        System.out.println();


        /******************************************************
         * syncing threads
         ******************************************************/
        System.out.println("=== Syncing Threads ===");

        TargetClass target = new TargetClass();

        SyncThread t1 = new SyncThread(1, target);
        SyncThread t2 = new SyncThread(2, target);
        SyncThread t3 = new SyncThread(3, target);

        t1.start();
        t2.start();
        t3.start();

        System.out.println();
    }

    /******************************************************
     * extending Thread class
     ******************************************************/
    public static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                int iterations = 7;
                for (int i = 0; i < iterations; i++) {
                    System.out.println("\t"+"From secondary thread");
                    sleep(2000);
                }
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }

    /******************************************************
     * implementing Runnable interface
     ******************************************************/
    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                int iterations = 5;
                for (int i = 0; i < iterations; i++) {
                    System.out.println("\t\t"+"From runnable");
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }

    /******************************************************
     * syncing threads
     ******************************************************/
    public static class SyncThread extends Thread {

        private int threadId;
        private TargetClass target;

        public SyncThread(int threadId, TargetClass target) {
            this.threadId = threadId;
            this.target = target;
        }

        @Override
        public void run() {
            synchronized (target) {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                target.call(threadId);
            }
        }
    }

    public static class TargetClass {

        public void call(int threadId) {
            System.out.println("Calling thread from "+threadId);
        }
    }

}
