package com.caper.psychological_counseling.controller;

import com.caper.psychological_counseling.common.config.exception.AjaxResponse;
import com.caper.psychological_counseling.common.config.exception.CustomException;
import com.caper.psychological_counseling.common.config.exception.CustomExceptionType;
import com.caper.psychological_counseling.model.vo.ConsultRecordVO;
import com.caper.psychological_counseling.model.vo.ConsultVO;
import com.caper.psychological_counseling.service.ConsultRecordService;
import com.caper.psychological_counseling.service.ConsultService;
import com.caper.psychological_counseling.service.ScheduleService;
import com.caper.psychological_counseling.service.VisitRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * author:chou
 *
 * 咨询助理
 */

@Slf4j
@RestController
@RequestMapping(value = "/assistant")
public class AssistantController {
    @Resource
    private ScheduleService scheduleService;

    @Resource
    private VisitRecordService visitRecordService;

    @Resource
    private ConsultService consultService;

    @Resource
    private ConsultRecordService consultRecordService;

    /**
     * 查看所有人的咨询表
     *
     * 输入：校区
     */
    @RequestMapping(value = "/consult/org/{org_id}", method = RequestMethod.GET)
    public AjaxResponse getConsults(@PathVariable("org_id") Long org_id) {
        //schedule_id
        List<Long> schedule_ids = scheduleService.selectByOrgId(org_id);

        //consult
        try {
            //application_id
            List<Long> application_ids = visitRecordService.getApplicationIdsByScheduleIds(schedule_ids);
            List<ConsultVO> consultVOS = consultService.getConsultsByApplicationIds(application_ids);
            return AjaxResponse.success(consultVOS);
        } catch (Exception e) {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR
                    ,"查询失败，校区不正确或不存在"));
        }
    }



    /**
     * 下拉框
     * 通过咨询表查看此人的的咨询记录
     */
    @RequestMapping(value = "/consultRecord/consult/{consult_id}", method = RequestMethod.GET)
    public AjaxResponse getConsultRecord(@PathVariable("consult_id") Long consult_id) {
        List<ConsultRecordVO> consultRecordVOS = consultRecordService.selectByConsultId(consult_id);

        return AjaxResponse.success(consultRecordVOS);
    }

}
