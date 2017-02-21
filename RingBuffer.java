public class RingBuffer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//
		RingBuffer ring1 = new RingBuffer(2);
		System.out.println(ring1.size());
		ring1.enqueue(1.0);
		System.out.println(ring1.size());
		System.out.println(ring1.peek());
		ring1.enqueue(2.0);
		System.out.println(ring1.peek());
		System.out.println(ring1.dequeue());
		System.out.println(ring1.peek());
		ring1.enqueue(1.0);
		System.out.println(ring1.peek());
		System.out.println(ring1.dequeue());
		System.out.println(ring1.peek());
	}

	private int first;
	private int size1;
	private int capacity1;
	public double[] ringBuffer;

	public RingBuffer(int capacity) {
		//This is the constructor for the ring buffer
		ringBuffer = new double[capacity];
		first = 0;
		size1 = 0;
		capacity1 = capacity;
	}

	public int size() {
		//This returns the current size of the ring buffer
		return (size1);
	}

	public boolean isEmpty() {
		//This checks if the ring buffer is empty
		if (size1 == 0) {
			return (true);
		} else {
			return (false);
		}
	}

	public boolean isFull() {
		//This checks if the ring buffer is full
		if (size1 == capacity1) {
			return (true);
		} else {
			return (false);
		}
	}

	public void enqueue(double x) {
		//This adds new data to the ring buffer
		if (capacity1 == size1) {
			throw new IllegalStateException("Ring Buffer full: cannont enqueue");
		}
		int index = (first + size1) % capacity1;
		ringBuffer[index] = x;
		size1++;
		

	}

	public double dequeue() {
		//This removes data from the ring buffer
		if (size1 == 0) {
			throw new IllegalStateException("Ring Buffer empty: cannot dequeue");
		}
		size1--;
		int pos = first;
		first++;
		if (first == ringBuffer.length) {
			first = 0;
		}
		return (ringBuffer[pos]);
	
	}

	public double peek() {
		//This returns the next piece of data to be dequeued
		if (size1 == 0) {
			throw new IllegalStateException("Ring Buffer empty: cannot peek");
		} else {
			return (ringBuffer[first]);
		}
	}

}
