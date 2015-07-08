public class Dog implements Pet {
	int happiness;

	public void reward(int numTimes) {
		happiness += 3;
	}
 	public void punish(int numTimes) {
 		happiness -= 2;
 	}
  	public void act() {
  		System.out.println("Dog's happiness is " + happiness + ". Woof.");
  	}
}