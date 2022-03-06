public class Main {
    public static void main(String[] args) {
        System.out.println("hi");

        AnimalGroup catGroup = new AnimalGroup();
        catGroup.add(new Cat());
        catGroup.add(new Cat());
        catGroup.add(new Cat());

        AnimalGroup dogGroup = new AnimalGroup();
        dogGroup.add(new Dog());
        dogGroup.add(new Dog());

        AnimalGroup zoo = new AnimalGroup();
        zoo.add(catGroup);
        zoo.add(dogGroup);

        zoo.speak();
    }
}
