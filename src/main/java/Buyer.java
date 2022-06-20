import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buyer implements Runnable {
    private final int BUY_TIME = 1000;
    private final List<Car> carList;
    private final Lock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();

    public Buyer(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (carList.size() == 0) {
                System.out.println("Машин нет");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
            carList.remove(0);
            Thread.sleep(BUY_TIME);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}