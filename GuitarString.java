import java.util.Arrays;

public class GuitarString {
	public static void main(String[] args) {
	
	}

	private RingBuffer ring1;


	public GuitarString(double frequency) {
		//This is one constructor for Guitar String, it is used when inputting a frequency
		ring1 = new RingBuffer((int) Math.ceil(44100 / frequency));
		for (int i = 0; i < (int) Math.ceil(44100 / frequency); i++) {
			ring1.enqueue(0.0);
		}

	}

	public GuitarString(double[] init) {
		//This is another constructor used when inputting an array
		ring1 = new RingBuffer(init.length);
		for (int i = 0; i < init.length; i++) {
			ring1.enqueue(init[i]);
		}
	}

	public void pluck() {
		//This plucks the string 
		for (int i = 0; i < ring1.ringBuffer.length; i++) {
			ring1.dequeue();
			ring1.enqueue(-0.5 + Math.random());
		}

	}

	private int ticCount = 0;

	public void tic() {
		//This increases the timestep of the guitar string
		double val1 = ring1.dequeue();
		double newVal = (0.994 * (val1 + ring1.peek())) / 2.0;
		ring1.enqueue(newVal);
		ticCount++;
	}

	public double sample() {
		//This returns the first piece of data from the guitar string
		return (ring1.peek());
	}

	public int time() {
		//This returns how many times the string has been ticked
		return (ticCount);
	}

}
