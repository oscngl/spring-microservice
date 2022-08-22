package com.oscngl.notification.service.impl;

import com.oscngl.clients.notification.NotificationRequest;
import com.oscngl.notification.mapper.NotificationMapper;
import com.oscngl.notification.repository.NotificationRepository;
import com.oscngl.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(NotificationMapper.INSTANCE.requestToNotification(notificationRequest));
        log.info("Notification sent to: " + notificationRequest.getCustomerEmail());
    }

}
