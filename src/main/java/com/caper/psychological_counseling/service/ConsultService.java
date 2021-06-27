package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.Consult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caper.psychological_counseling.model.vo.ConsultVO;
import com.caper.psychological_counseling.model.vo.ScheduleVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface ConsultService extends IService<Consult> {

    //查询符合条件的咨询师
    List<ScheduleVO> find_consults(Long area_id, String type);

    //创建咨询表
    void build_consult (Consult consult);

    //用户评价咨询
    void update_evaluate(String evaluate,Long id);

    //添加咨询状态
    void update_consultStatus(Integer status,Long id);

    //添加咨询结论
    void update_consultResult(String result,Long id);


    //添加咨询类型
    void update_consultType(String type,Long id);

    //查看咨询表（根据状态）
    List<ConsultVO> selectConsult(Long id, Integer status);


    //查询排班表的日期、time、校区、咨询师。根据排班表id
    ScheduleVO find_schedule(Long id);



    //根据时间、time、校区、咨询师查找排班id

    Long  find_scheduleId(Long area_id,Date date,String begin_time,String end_time,Long user_id);




    List<ConsultVO> getConsultsByApplicationIds(List<Long> application_ids);
}
