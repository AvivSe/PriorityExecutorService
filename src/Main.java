import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args) {
        PriorityExecutorService<DemoTask> priorityExecutorService =
                new PriorityExecutorService<>(Executors.newFixedThreadPool(2), new PriorityBlockingQueue<>(),3000);


        // Set timeout to priorityExecutorService
        new Thread(()-> {
            try {
                Thread.sleep(6000);
                System.out.println("Shouting down Executor");
                priorityExecutorService.shutdownExecutor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Random r = new Random();
        for(int i = 1; i <= 4; i++) {
            priorityExecutorService.add((new DemoTask(i,r.nextInt(2000))));
        }


    }
}
