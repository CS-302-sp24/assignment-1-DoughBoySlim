import java.util.Random;

class Philosopher extends Thread {
    private Chopstick left, right;
    private Random random;
    private int thinkCount;

    public Philosopher(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
        random = new Random();
    }

    public void run() {
        try {
            while (true) {
                ++thinkCount;
                if (thinkCount % 10 == 0)
                    System.out.println("Philosopher " + getId() + " has thought " + thinkCount + " times");
                Thread.sleep(random.nextInt(1000));     // Think for a while
                synchronized (left) {                    // Grab left chopstick
                    System.out.println("Philosopher " + getId() + " wants left chopstick " + left.getId());
                    synchronized (right) {                 // Grab right chopstick
                        System.out.println("Philosopher " + getId() + " wants right chopstick " + right.getId());
                        Thread.sleep(random.nextInt(1000)); // Eat for a while
                    }
                    System.out.println("Philosopher " + getId() + " released right chopstick " + right.getId());
                }
                System.out.println("Philosopher " + getId() + " released left chopstick " + left.getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
