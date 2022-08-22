package com.oscngl.notification.service;

import com.oscngl.clients.notification.NotificationRequest;

public interface NotificationService {

    void send(NotificationRequest notificationRequest);

}
