package com.yuthika.app.notification.rabbitmq;

import com.yuthika.app.clients.notification.NotificationRequest;
import com.yuthika.app.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest request) throws InterruptedException {
        log.info("Consumed {} from queue", request);
        notificationService.send(request);
        Thread.sleep(1000);
    }
}
