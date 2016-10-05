package org.rastko.reactive.spring.engine;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rastko.reactive.spring.PlaygroundApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rastko.reactive.spring.engine.EngineBootstrapper.PRIMARY_NOTIFICATION_KEY;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlaygroundApplication.class)
public class ReactiveEngineIT {

    @Autowired
    private EventBus bus;

    @Autowired
    private NotificationReceiver receiver;

    private static Random random;

    @BeforeClass
    public static void classInit() {
        random = new Random();
    }

    @Test
    public void busShouldBeAvailable() {
        bus.notify(PRIMARY_NOTIFICATION_KEY, Event.wrap(random.nextLong()));

        final Optional<Long> value = receiver.pollForNotification(500);

        assertThat(value.isPresent()).describedAs("Well, event should have arrived so far...").isTrue();
    }
}
