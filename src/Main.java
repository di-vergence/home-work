import consumer.Consumer;
import dto.Message;
import generator.MessageGenerator;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        Integer numberOfConsumers = 4;
        Integer stringLength = 5;

        List<PriorityBlockingQueue<Message>> queues = Stream.generate((Supplier<PriorityBlockingQueue<Message>>) PriorityBlockingQueue::new)
                .limit(numberOfConsumers)
                .collect(toList());

        Thread generatorThread = new Thread(new MessageGenerator(queues, stringLength));
        generatorThread.start();

        queues.stream()
                .map(Consumer::new)
                .forEach(consumer -> new Thread(consumer).start());
    }
}