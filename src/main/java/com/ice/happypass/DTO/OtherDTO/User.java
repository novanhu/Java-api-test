package com.ice.happypass.DTO.OtherDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private int age;
    private String city;
    private String email;
}