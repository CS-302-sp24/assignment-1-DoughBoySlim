public class DiningPhilosophers {

    public static void main(String[] args) throws InterruptedException {
        // This is for setting the variables up for the main to pick up what happened in the function
        int np = Integer.parseInt(args[0]); // # Philosopher
        int nc = Integer.parseInt(args[1]); // # Cycles
        int tt = Integer.parseInt(args[2]); // Thinking time
        int et = Integer.parseInt(args[3]); // Eating Time
        int rl = Integer.parseInt(args[4]); // Right or Left Handed
      

        Philosopher[] philosophers = new Philosopher[np];
        Chopstick[] chopsticks = new Chopstick[np];  


      for (int i = 0; i < 5; ++i)
        chopsticks[i] = new Chopstick(i);
      for (int i = 0; i < 5; ++i) {
        philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % 5]);
        philosophers[i].start();
      }
      for (int i = 0; i < 5; ++i)
        philosophers[i].join();
    }
  }