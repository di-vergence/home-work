package consumer;

import dto.Message;

import java.util.concurrent.PriorityBlockingQueue;

public class Consumer implements Runnable {

    private PriorityBlockingQueue<Message> queue;

    private final long DELAY = 1000L;

    public Consumer(PriorityBlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = queue.take();
                Thread.sleep(DELAY);
                System.out.println(message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
