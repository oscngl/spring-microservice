package com.oscngl.subscription.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {

    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email is not valid")
    private String customerEmail;

}
