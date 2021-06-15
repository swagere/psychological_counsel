package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.service.ApplicationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
public class ApplicationController{
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResponse getByIds(@PathVariable("id") Long id) {
        return AjaxResponse.success(applicationService.getById(id));
    }

}
