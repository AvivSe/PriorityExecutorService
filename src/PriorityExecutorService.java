import java.util.concurrent.*;

public class PriorityExecutorService<T extends Comparable & Runnable> {

    private ExecutorService executor;
    private PriorityBlockingQueue<T> queue;
    private boolean stop;

    public PriorityExecutorService(ExecutorService executorService) {
        this.executor = executorService;
        queue = new PriorityBlockingQueue<T>();
        stop = false;
        activeExecutor();

    }

    private void activeExecutor() {
        new Thread(()-> {
            while(!stop) {
                try {
                    executor.execute(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    void shutdownExecutor() {
        stop = true;
        executor.shutdown();
    }

    void add(T obj) {
        queue.offer(obj);
    }

}
