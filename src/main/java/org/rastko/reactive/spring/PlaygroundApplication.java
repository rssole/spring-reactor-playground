package org.rastko.reactive.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import reactor.spring.context.config.EnableReactor;

@EnableReactor
@SpringBootApplication
public class PlaygroundApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(PlaygroundApplication.class, args);
        ctx.registerShutdownHook();
    }
}
