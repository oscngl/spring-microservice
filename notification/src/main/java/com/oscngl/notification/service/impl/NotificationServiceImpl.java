package com.oscngl.notification.service.impl;

import com.oscngl.clients.notification.NotificationRequest;
import com.oscngl.notification.mapper.NotificationMapper;
import com.oscngl.notification.repository.NotificationRepository;
import com.oscngl.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(NotificationMapper.INSTANCE.requestToNotification(notificationRequest));
    }

}
