package com.yuthika.app.clients.notification;

public record NotificationRequest(
        String message,
        String sender,
        String toCustomerEmail,
        Integer toCustomerId
) {
}