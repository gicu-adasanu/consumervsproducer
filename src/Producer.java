public class Producer implements Runnable {
    private final Stock stock;

    public Producer(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("producer-" + Thread.currentThread().getId());
        while (true) {
            try {
                stock.setProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
