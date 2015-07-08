public class Histogram {
	public int[] hist;

	// Constructor
	public Histogram() {
		hist = new int[10];
	}

	// Method to Add Score to bins of 5 and Keep Tally
	void addScore(int score) {
		for (int i = 0; i < 10; i++) {
		if (score >= (i*5+1) && score <= (i*5+5)) {
			hist[i] += 1;
		}
	}
	}

	// Method to Print Resulting Histogram
	void print() {
		System.out.print("1-5    | "); for (int i=0; i<hist[0];i++) {System.out.print('*');}; System.out.println();
		System.out.print("6-10   | "); for (int i=0; i<hist[1];i++) {System.out.print('*');}; System.out.println();
		System.out.print("11-15  | "); for (int i=0; i<hist[2];i++) {System.out.print('*');}; System.out.println();
		System.out.print("16-20  | "); for (int i=0; i<hist[3];i++) {System.out.print('*');}; System.out.println();
		System.out.print("21-25  | "); for (int i=0; i<hist[4];i++) {System.out.print('*');}; System.out.println();
		System.out.print("26-30  | "); for (int i=0; i<hist[5];i++) {System.out.print('*');}; System.out.println();
		System.out.print("31-35  | "); for (int i=0; i<hist[6];i++) {System.out.print('*');}; System.out.println();
		System.out.print("36-40  | "); for (int i=0; i<hist[7];i++) {System.out.print('*');}; System.out.println();
		System.out.print("41-45  | "); for (int i=0; i<hist[8];i++) {System.out.print('*');}; System.out.println();
		System.out.print("45-50  | "); for (int i=0; i<hist[9];i++) {System.out.print('*');}; System.out.println();
	}

	// Test input and resulting output

	public static void main(String[] args) {
        Histogram h = new Histogram();
        h.addScore(25);
        h.addScore(36);
        h.addScore(12);
        h.addScore(37);
        h.addScore(4);
        h.addScore(22);
        h.addScore(50);
        h.print();
}

}