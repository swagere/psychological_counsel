package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: chou
 */

@Slf4j
@RestController
public class MonitorController {

    /**
     * 返回按周的common排班表
     * @return
     */
    @RequestMapping(value = "/commonSchedules/{role_id}", method = RequestMethod.GET)
    public AjaxResponse getWeekSchedules(@PathVariable("role_id")Integer role_id) {
        return AjaxResponse.success();
    }
}
