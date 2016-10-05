package org.rastko.reactive.spring.engine;


public class EventProcessingFailedException extends RuntimeException {
    public EventProcessingFailedException(Exception e) {
        super(e);
    }
}
