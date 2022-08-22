package com.oscngl.notification.mapper;

import com.oscngl.clients.notification.NotificationRequest;
import com.oscngl.notification.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    Notification requestToNotification(NotificationRequest notificationRequest);
    NotificationRequest notificationToRequest(Notification notification);
    List<Notification> requestsToNotifications(List<NotificationRequest> notificationRequests);
    List<NotificationRequest> notificationsToRequests(List<Notification> notifications);

}
