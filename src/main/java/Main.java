import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            new Thread(new CarMaker(carList), "Производитель BMW").start();
            new Thread(new Buyer(carList), "Покупатель 1").start();
            new Thread(new Buyer(carList), "Покупатель 2").start();
            new Thread(new Buyer(carList), "Покупатель 3").start();
        }
    }
}