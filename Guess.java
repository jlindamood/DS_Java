import java.lang.Math;
import java.util.Scanner;

public class Guess {
	public static void main(String[] args) {

		// Random Int (0-100) Generation

		int num = (int)(Math.random() * 101 + 1);

		// Scanner for number input

		Scanner s = new Scanner(System.in);

		System.out.println("Guess a number (1-100): ");
		int guess = s.nextInt();

		// Returns hints and accepts new inputs until correct number is guessed.

		while (guess != num) {
		if (guess > num) {
			System.out.println("Smaller!");
			guess = s.nextInt();
		} else if (guess < num) {
			System.out.println("Larger!");
			guess = s.nextInt();
		}
	}
		System.out.println("You got it!");
	}
}