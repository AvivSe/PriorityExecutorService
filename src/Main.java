import java.util.Scanner;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        PriorityExecutorService<DemoTask> priorityExecutorService = new PriorityExecutorService<DemoTask>(Executors.newFixedThreadPool(2));

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


        for(int i = 1; i <= 4; i++) {
            priorityExecutorService.add((new DemoTask(i,2000/i)));
        }


    }
}
