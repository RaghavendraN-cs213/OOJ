public class DeadlockExample {

    public static void main(String[] args) {
        final SharedResource sharedResource = new SharedResource();

        Thread process1 = new Thread(() -> {
            try {
                sharedResource.method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread process2 = new Thread(() -> {
            try {
                sharedResource.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        process1.start();
        process2.start();
    }
}

class SharedResource {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() throws InterruptedException {
        synchronized (lock1) {
            System.out.println("Method 1 acquired lock1");
            Thread.sleep(1000);

            synchronized (lock2) {
                System.out.println("Method 1 acquired lock2");
                // Perform some task using both lock1 and lock2
            }
        }
    }

    public void method2() throws InterruptedException {
        synchronized (lock2) {
            System.out.println("Method 2 acquired lock2");
            Thread.sleep(1000);

            synchronized (lock1) {
                System.out.println("Method 2 acquired lock1");
                // Perform some task using both lock1 and lock2
            }
        }
    }
}