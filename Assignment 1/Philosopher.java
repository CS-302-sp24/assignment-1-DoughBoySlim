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