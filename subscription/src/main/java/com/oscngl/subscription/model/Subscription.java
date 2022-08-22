package com.oscngl.subscription.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subscription")
public class Subscription {

    @Transient
    public static final String SEQUENCE_NAME = "subscriptions_sequence";

    @Id
    private Long id;
    private String customerEmail;
    private LocalDateTime createdAt = LocalDateTime.now();

}
