public class GuitarHero {
	public static void main(String[] args) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdccfvgbnjmk,.;/'";
		GuitarString[] listOfStrings = new GuitarString[keyboard.length()];

		for (int i = 0; i < keyboard.length(); i++) {
			//This fills an array with guitar strings of linear frequency
			double frequency = 440.0 * Math.pow(1.05956, i - 24);
			listOfStrings[i] = new GuitarString(frequency);
		}

		while (true) {//This checks if a key has been typed, then plucks the string correspondent to that key
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				if (0 <= keyboard.indexOf(key)) {
					listOfStrings[keyboard.indexOf(key)].pluck();
				}
			}
			double sample = 0.0;
			for (int i = 0; i < keyboard.length(); i++) {
				sample = sample + listOfStrings[i].sample();
			}
			StdAudio.play(sample);
			for (int i = 0; i < keyboard.length(); i++) {
				listOfStrings[i].tic();
			}

		}

	}

}
