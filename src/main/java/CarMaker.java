import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarMaker implements Runnable {
    private final int CREATE_TIME = 1500;
    private final List<Car> carList;
    private final Lock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();

    public CarMaker(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " выпустил 1 авто");
            carList.add(new Car());
            Thread.sleep(CREATE_TIME);
            condition.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}