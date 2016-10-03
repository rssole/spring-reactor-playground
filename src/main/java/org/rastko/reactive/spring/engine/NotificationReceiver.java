package org.rastko.reactive.spring.engine;

import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * Simply aiming to receive notification
 */
@Service
public class NotificationReceiver implements Consumer<Event<Long>> {
    public void accept(Event<Long> e) {
        System.out.println(String.format("Received notification for id: %d", e.getData()));
    }
}
