class DisplayThread extends Thread {
    private String message;
    private int interval;

    public DisplayThread(String message, int interval) {
        this.message = message;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(message);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class threads {
    public static void main(String[] args) {
        // Creating two threads
        DisplayThread thread1 = new DisplayThread("BMS College of Engineering", 2000); // 4 seconds
        DisplayThread thread2 = new DisplayThread("CSE", 1000); // 2 seconds

        // Starting the threads
        thread1.start();
        thread2.start();
    }
}
