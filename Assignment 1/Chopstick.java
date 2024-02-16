class Chopstick {
	private int id;
	private boolean held; // This is to tell if a chopstick is being held
	public Chopstick(int id) { this.id = id; }
 	public int getId() { return id; }

	// Writing a function that the philosopher can pick up a chopstick
	public void Grab(int philosopherId) throws InterruptedException {

		// If chopstick is take wait
		while(held){
			wait();
		}

		// If philosopher picks up chopstick, write to terminal to say thats what happened

		held = true;
		System.out.println("Philosopher" + philosopherId + "picked up chopstick" + id);
	
	}

	// Function for the philosopher to let go of the chopstick when done eating

	public void Drop(int philosopherId) {
		// Letting Go of Chopstick
		held = false;
		System.out.println("Philosopher" + philosopherId + "dropped chopstick" + id);
	}

}
