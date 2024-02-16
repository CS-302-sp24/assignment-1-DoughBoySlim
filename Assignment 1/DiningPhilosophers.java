public class DiningPhilosophers {

  public static void main(String[] args) throws InterruptedException {
      if (args.length != 5) {
          System.err.println("Usage: java DiningPhilosophers <np> <nc> <tt> <et> <rl>");
          System.exit(1);
      }

      int np = Integer.parseInt(args[0]); // Number of philosophers
      int nc = Integer.parseInt(args[1]); // Number of cycles
      int tt = Integer.parseInt(args[2]); // Maximum thinking time
      int et = Integer.parseInt(args[3]); // Maximum eating time
      int rl = Integer.parseInt(args[4]); // Right/left-handedness

      Philosopher[] philosophers = new Philosopher[np];
      Chopstick[] chopsticks = new Chopstick[np];

      for (int i = 0; i < np; i++)
          chopsticks[i] = new Chopstick(i);

      for (int i = 0; i < np; i++) {
          Chopstick leftChopstick = chopsticks[i];
          Chopstick rightChopstick = chopsticks[(i + 1) % np];
          // Determine handedness based on rl parameter
          if (rl == 1 && i % 2 == 1)
              philosophers[i] = new Philosopher(i, nc, tt, et, leftChopstick, rightChopstick);
          else
              philosophers[i] = new Philosopher(i, nc, tt, et, rightChopstick, leftChopstick);
          philosophers[i].start();
      }

      // Wait for all philosophers to finish
      for (Philosopher philosopher : philosophers)
          philosopher.join();
  }
}
