package com.edu.ecom.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
