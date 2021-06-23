package com.caper.psychological_counseling.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    private String name;

    private Long telephone;

    private String email;

    private Integer gender;

    private String description;

    private Integer grade;
}
