import java.util.Random;

class Philosopher extends Thread {
    private int philosopherId; // Changed from 'id' to 'philosopherId'
    private Chopstick left, right;
    private Random random;
    private int thinkCount;
    private int cycles;
    private int maxThinkingTime;
    private int maxEatingTime;

    public Philosopher(int philosopherId, int cycles, int maxThinkingTime, int maxEatingTime, Chopstick left, Chopstick right) {
        this.philosopherId = philosopherId; // Changed from 'id' to 'philosopherId'
        this.cycles = cycles;
        this.maxThinkingTime = maxThinkingTime;
        this.maxEatingTime = maxEatingTime;
        this.left = left;
        this.right = right;
        random = new Random();
    }

    public int getPhilosopherId() { // Renamed from 'getId' to 'getPhilosopherId'
        return philosopherId; // Changed from 'id' to 'philosopherId'
    }

    public void run() {
        try {
            for (int cycle = 0; cycle < cycles || cycles == 0; cycle++) {
                ++thinkCount;
                System.out.println("Philosopher " + philosopherId + " thinks for " + thinkCount + " units");
                Thread.sleep(random.nextInt(maxThinkingTime)); // Think for a while

                if (cycle < cycles - 1 || cycles == 0) {
                    System.out.println("Philosopher " + philosopherId + " wants right chopstick");
                    left.pickUp(this); // Grab left chopstick
                    System.out.println("Philosopher " + philosopherId + " has right chopstick");
                    System.out.println("Philosopher " + philosopherId + " wants left chopstick");
                    right.pickUp(this); // Grab right chopstick
                    System.out.println("Philosopher " + philosopherId + " has left chopstick");
                    Thread.sleep(random.nextInt(maxEatingTime)); // Eat for a while
                    left.putDown(); // Release left chopstick
                    System.out.println("Philosopher " + philosopherId + " releases left chopstick");
                    right.putDown(); // Release right chopstick
                    System.out.println("Philosopher " + philosopherId + " releases right chopstick");
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Philosopher " + philosopherId + " interrupted");
        }
    }
}

