public class PetMain {

public static void main(String[] args) {
  Pet fido = new Dog();
  Pet socks = new Cat();

  fido.reward(4);
  socks.punish(2);

  fido.act();
  socks.act();
  }
}