import java.util.ArrayList;
import java.util.List;

public class AnimalGroup implements Animal{

    private List<Animal> animals;

    public AnimalGroup(){
        this.animals = new ArrayList<>();
    }

    @Override
    public void speak() {
        System.out.println("ANIMAL GROUP SPEAK!");
        animals.forEach(Animal::speak);
    }

    public void add(Animal animal) {
        this.animals.add(animal);
    }
}
