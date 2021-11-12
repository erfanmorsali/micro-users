package com.example.microusers.modules.users.eventHandlers;

import com.example.microusers.configs.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserDeletedEventListener {


    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserDeletedEventListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @EventListener
    public void processUserCreatedEvent(UserDeletedEvent userDeletedEvent) {
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE, RabbitmqConfig.ROUTING_KEY, userDeletedEvent);
    }
}
