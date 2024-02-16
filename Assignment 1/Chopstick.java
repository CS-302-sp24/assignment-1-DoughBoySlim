class Chopstick {
	private int id;
	private Philosopher holder;
	public Chopstick(int id) { this.id = id; }
 	public int getId() { return id; }

	public synchronized void pickUp(Philosopher philosopher) {
		while (holder != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		holder = philosopher;
		System.out.println("Philosopher" + philosopher.getId() + "picks up chopstick " + id);
	}	

	public synchronized void putDown() {
		holder = null;
		notify();
	}

}
