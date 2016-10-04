package org.rastko.reactive.spring.engine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rastko.reactive.spring.PlaygroundApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.bus.EventBus;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlaygroundApplication.class)
public class ReactiveEngineIT {

    @Autowired
    private EventBus bus;

    @Test
    public void busShouldBeAvailable() {
        // TODO: Write some test :)
    }
}
