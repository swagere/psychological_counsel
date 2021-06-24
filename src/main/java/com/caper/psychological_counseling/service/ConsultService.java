package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Consult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.ScheduleVO;

import java.util.Date;
import java.util.List;

public interface ConsultService extends IService<Consult> {

    //查询符合条件的咨询师
    List<ScheduleVO> find_consults(Long area_id, String type);

    //创建咨询表
    void build_consult (Consult consult);

}
