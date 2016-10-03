package org.rastko.reactive.spring.engine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.bus.EventBus;

import javax.annotation.PostConstruct;

import static reactor.bus.selector.Selectors.$;

@Component
public class EngineBootstrapper {

    private EventBus eventBus;
    private NotificationReceiver receiver;

    @Autowired
    public EngineBootstrapper(EventBus eventBus, NotificationReceiver receiver) {
        this.eventBus = eventBus;
        this.receiver = receiver;
    }

    @PostConstruct
    public void init() {
        eventBus.on($("hello-update"), receiver);
    }
}
