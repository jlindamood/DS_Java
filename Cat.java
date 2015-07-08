public class Cat implements Pet {
	int happiness;

	public void reward(int numTimes) {
		happiness += 1;
	}
 	public void punish(int numTimes) {
 		happiness -= 3;
 	}
 	public void act() {
 		System.out.println("Cat's happiness is " + happiness + ". Meow.");
 	}
 }