package com.oscngl.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotNull(message = "First Name can not be null")
    @NotEmpty(message = "First Name can not be empty")
    @NotBlank(message = "First Name can not be blank")
    private String firstName;

    @NotNull(message = "Last Name can not be null")
    @NotEmpty(message = "Last Name can not be empty")
    @NotBlank(message = "Last Name can not be blank")
    private String lastName;

    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, max = 20, message = "Password must contain at least 8 and maximum 20 characters")
    @Pattern(regexp = "^[0-9a-zA-Z]*$", message = "Password is not valid")
    private String password;

}
