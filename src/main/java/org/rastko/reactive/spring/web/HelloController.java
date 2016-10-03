package org.rastko.reactive.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.time.LocalDateTime;

@RestController
public class HelloController {

    private final EventBus eventBus;

    @Autowired
    public HelloController(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/{id}")
    public String updateSomething(@PathVariable Long id) {
        eventBus.notify("hello-update", Event.wrap(id));
        return "Updated!".concat(LocalDateTime.now().toString());
    }
}
