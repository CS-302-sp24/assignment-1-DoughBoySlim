public class DiningPhilosophers {
    public static void main(String[] args) {
        // Parse command line arguments
        if (args.length != 5) {
            System.out.println("Usage: java DiningPhilosophers np nc tt et rl");
            return;
        }

        int np = Integer.parseInt(args[0]); // Number of philosophers
        int nc = Integer.parseInt(args[1]); // Number of cycles
        int tt = Integer.parseInt(args[2]); // Maximum thinking time (milliseconds)
        int et = Integer.parseInt(args[3]); // Maximum eating time (milliseconds)
        int rl = Integer.parseInt(args[4]); // Right or left-handed

        // Creating chopsticks
        Chopstick[] chopsticks = new Chopstick[np];
        for (int i = 0; i < np; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        // Creating philosophers
        Philosopher[] philosophers = new Philosopher[np];
        for (int i = 0; i < np; i++) {
            int leftChopstickIndex = i;
            int rightChopstickIndex = (i + 1) % np;
            if (rl == 1 && i % 2 != 0) { // Left-handed philosopher
                leftChopstickIndex = (i + 1) % np;
                rightChopstickIndex = i;
            }
            philosophers[i] = new Philosopher(i, chopsticks[leftChopstickIndex], chopsticks[rightChopstickIndex], nc, tt, et);
        }

        // Start philosophers' threads
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }
}

class Philosopher extends Thread {
    private int id;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private int numCycles;
    private int maxTt;
    private int maxEt;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick, int numCycles, int maxTt, int maxEt) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.numCycles = numCycles;
        this.maxTt = maxTt;
        this.maxEt = maxEt;
    }

    @Override
    public void run() {
        try {
            for (int cycle = 0; numCycles == 0 || cycle < numCycles; cycle++) {
                // Thinking
                int thinkingTime = (int) (Math.random() * maxTt);
                System.out.println("Philosopher " + id + " thinks for " + thinkingTime + " units");
                Thread.sleep(thinkingTime);

                // Eating
                System.out.println("Philosopher " + id + " wants right chopstick");
                rightChopstick.Grab(id);
                System.out.println("Philosopher " + id + " has right chopstick");
                System.out.println("Philosopher " + id + " wants left chopstick");
                leftChopstick.Grab(id);
                System.out.println("Philosopher " + id + " has left chopstick");

                int eatingTime = (int) (Math.random() * maxEt);
                System.out.println("Philosopher " + id + " eats for " + eatingTime + " units");
                Thread.sleep(eatingTime);

                // Releasing chopsticks
                leftChopstick.Drop(id);
                rightChopstick.Drop(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}