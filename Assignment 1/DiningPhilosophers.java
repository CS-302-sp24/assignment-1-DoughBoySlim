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

