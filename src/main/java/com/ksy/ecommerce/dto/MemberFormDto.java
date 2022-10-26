package com.ksy.ecommerce.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class MemberFormDto {

    @NotBlank(message = "name required") //길이 , " " 까지 검사
    private String name;
    @NotBlank(message = "email required")
    @Email(message = "email 형식")
    private String email;
    @NotBlank(message = "password required")
    @Length(min = 8, max = 16, message = "should be 8-16")
    private String password;
    @NotEmpty(message = "address required")
    private String address;
}
