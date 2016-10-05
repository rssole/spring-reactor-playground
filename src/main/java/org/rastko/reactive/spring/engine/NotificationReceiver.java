package org.rastko.reactive.spring.engine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Simply aiming to receive notification
 */
@Service
public class NotificationReceiver implements Consumer<Event<Long>> {

    @Value("${notifications.bufferSize:42}")
    private int bufferSize;

    @Value("${notifications.acceptTimeout:3000}")
    private long acceptTimeOut;

    private BlockingQueue<Long> currentNotificationsBuffer;

    @PostConstruct
    public void init() {
        currentNotificationsBuffer = new LinkedBlockingQueue<>(bufferSize);
    }

    /**
     * Should receive first value in queue should any exist
     *
     * @param timeout period in milliseconds to wait for value to be fetched
     * @return first value available or empty optional if no value available
     */
    public Optional<Long> pollForNotification(int timeout) {
        try {
            return Optional.ofNullable(currentNotificationsBuffer.poll(timeout, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            return Optional.empty();
        }
    }

    public void accept(Event<Long> e) {
        try {
            currentNotificationsBuffer.offer(e.getData(), acceptTimeOut, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ie) {
            // TODO: Somewhere persist event whose accepting failed
            throw new EventProcessingFailedException(ie);
        }
    }
}
