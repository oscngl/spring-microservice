package com.oscngl.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to " + exchange + " using routing key " + routingKey + ". Payload: " + payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to " + exchange + " using routing key " + routingKey + ". Payload: " + payload);
    }

}
