import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Stock {
    private static AtomicInteger myStock;
    private static AtomicBoolean isClosed;
    private final int min;
    private final int max;

    public Stock(int min, int max) {
        myStock = new AtomicInteger(0);
        isClosed = new AtomicBoolean(true);
        this.min = min;
        this.max = max;
    }

    public void getProduct() throws InterruptedException {
        while (myStock.get() < min || isClosed.get()) {
            synchronized (this) {
                isClosed.set(true);
                wait();
            }
        }
        System.out.println("Consumer " + Thread.currentThread().getId() + " get product. Stock: " + myStock.decrementAndGet());
        Thread.sleep(500);
    }

    public void setProduct() throws InterruptedException {
        if (myStock.get() > max) {
            synchronized (this) {
                isClosed.set(false);
                notifyAll();
            }
        }
        System.out.println("Producer " + Thread.currentThread().getId() + " add product. Stock: " + myStock.incrementAndGet());
        Thread.sleep(500);
    }
}
