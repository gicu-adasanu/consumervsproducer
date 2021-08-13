import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Thread> producersList = new ArrayList<>();
    public static List<Thread> consumersList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Enter number of producers: ");
        Scanner scanner = new Scanner(System.in);
        int producers = scanner.nextInt();
        System.out.println("Enter number of consumers: ");
        int consumers = scanner.nextInt();
        System.out.println("Enter min: ");
        int min = scanner.nextInt();
        System.out.println("Enter max: ");
        int max = scanner.nextInt();
        Stock stock = new Stock(min, max);

        while (producers > 0) {
            Thread producer = new Thread(new Producer(stock));
            producersList.add(producer);
            producers--;
        }

        while (consumers > 0) {
            Thread consumer = new Thread(new Consumer(stock));
            consumersList.add(consumer);
            consumers--;
        }

        new Thread(() -> {
            producersList.forEach(Thread::start);
        }).start();

        new Thread(() -> {
            consumersList.forEach(Thread::start);
        }).start();
    }
}
