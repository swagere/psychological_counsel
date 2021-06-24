package com.caper.psychological_counseling.model.vo;

import lombok.Data;

@Data
public class ApplicationVO {

    private Long id;

    private Integer rank;

    private String type;

    private String answer;

    private Integer urgency;

    private String description;


}
