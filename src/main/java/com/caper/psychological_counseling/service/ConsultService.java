package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Consult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.ScheduleVO;

import java.util.Date;
import java.util.List;

public interface ConsultService extends IService<Consult> {

    List<ScheduleVO> find_consults(Long area_id, String type);

}
