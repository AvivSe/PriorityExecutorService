import javafx.concurrent.Task;

import java.util.concurrent.Callable;

public class DemoTask implements Runnable, Comparable<DemoTask> {
    int runingTime;
    int id;

    public DemoTask(int id, int runingTime) {
        this.id = id;
        this.runingTime = runingTime;
        System.out.println(id + " is a new task that takes: "+runingTime + " to run.");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(runingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(id + " is done.");
    }

    @Override
    public int compareTo(DemoTask o) {
        return Integer.compare(o.runingTime, this.runingTime)*-1;
    }
}
