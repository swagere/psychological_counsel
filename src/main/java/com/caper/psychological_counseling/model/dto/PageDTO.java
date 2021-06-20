package com.caper.psychological_counseling.model.dto;

import lombok.Data;

/**
 * 分页器
 */

@Data
public class PageDTO {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
}
