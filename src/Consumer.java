public class Consumer implements Runnable {
    private final Stock stock;

    public Consumer(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("consumer-" + Thread.currentThread().getId());
        while (true) {
            try {
                stock.getProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
