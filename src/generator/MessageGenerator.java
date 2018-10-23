package generator;

import dto.Message;

import java.util.List;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class MessageGenerator implements Runnable {
    private Random random = new Random();
    private List<PriorityBlockingQueue<Message>> queues;

    private final int LETTER_A = 97; // letter 'a'
    private final int LETTER_Z = 122; // letter 'z'
    private int stringLength;
    private StringBuilder stringBuilder = new StringBuilder();
    private final int MAX_PRIORITY = 10;

    private final long WORK_DURATION = 1000L;
    private final long DELAY = 60L * 60L * 1000L;

    public MessageGenerator(List<PriorityBlockingQueue<Message>> queues, int stringLength) {
        this.queues = queues;
        this.stringLength = stringLength;
    }

    private Message generateMessage() throws IllegalStateException {
        stringBuilder.setLength(0);
        for (int i = 0; i < stringLength; i++) {
            int randomInt = LETTER_A + random.nextInt(LETTER_Z - LETTER_A + 1);
            stringBuilder.append((char) randomInt);
        }
        return new Message(stringBuilder.toString(), random.nextInt(MAX_PRIORITY));
    }

    @Override
    public void run() {
        while (true) {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < WORK_DURATION) {
                Message message = generateMessage();
                queues.forEach(queue -> queue.add(message));
            }
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
