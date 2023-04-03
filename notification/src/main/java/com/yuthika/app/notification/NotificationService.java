package com.yuthika.app.notification;

import com.yuthika.app.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .message(notificationRequest.message())
                        .sender(notificationRequest.sender())
                        .sentAt(LocalDateTime.now())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .toCustomerId(notificationRequest.toCustomerId())
                        .build()
        );
    }
}
