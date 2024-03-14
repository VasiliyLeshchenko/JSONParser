import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private int[] numbers;
    private Double test;
    private boolean man;
    private Dog dog;
    private List<Dog> dogList = new ArrayList<>();

    public User() {
    }

    public User(int id, String name, int[] numbers, Double test, boolean man, Dog dog, List<Dog> dogList) {
        this.id = id;
        this.name = name;
        this.numbers = numbers;
        this.test = test;
        this.man = man;
        this.dog = dog;
        this.dogList = dogList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
