package com.ksy.ecommerce.dto;

import lombok.Data;

@Data
public class MemberFormDto {

    private String name;

    private String email;

    private String password;

    private String address;
}