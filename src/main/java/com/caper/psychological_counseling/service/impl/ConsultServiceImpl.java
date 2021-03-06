package com.caper.psychological_counseling.service.impl;

import com.caper.psychological_counseling.model.domain.Consult;
import com.caper.psychological_counseling.mapper.ConsultMapper;
import com.caper.psychological_counseling.model.vo.ConsultVO;
import com.caper.psychological_counseling.model.vo.ScheduleVO;
import com.caper.psychological_counseling.service.ConsultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ConsultServiceImpl extends ServiceImpl<ConsultMapper, Consult> implements ConsultService {

    @Resource
    ConsultMapper consultMapper;


    //查询符合条件的咨询师
    @Override
    public List<ScheduleVO> find_consults(Long area_id, String type){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date end_time = calendar.getTime();//7天后的时间


        return  consultMapper.find_consults(area_id, type, date,end_time);
    }

    //创建咨询表
    @Override
    public void build_consult (Consult consult){
        consultMapper.insert(consult);

    }

    //查询排班表的日期、time、校区、咨询师。根据排班表id
    @Override
    public ScheduleVO find_schedule(Long id){
        return consultMapper.find_schedule(id);
    }



    //根据时间、time、校区、咨询师查找排班id
    @Override
    public Long  find_scheduleId(Long area_id,Date date,String begin_time,String end_time,Long user_id){
        return consultMapper.find_scheduleId(area_id, date, begin_time, end_time, user_id);
    }

    //用户评价咨询
    @Override
    public void update_evaluate(String evaluate,Long id){
        consultMapper.update_evaluate(evaluate, id);
    }

    @Override
    public List<ConsultVO> getConsultsByApplicationIds(List<Long> application_ids) {
        return consultMapper.selectConsultsByApplicationIds(application_ids);
    }

    //添加咨询状态
    @Override
    public void update_consultStatus(Integer status,Long id){
        consultMapper.update_consultStatus(status, id);
    }

    //添加咨询结论
    @Override
    public void update_consultResult(String result,Long id){
        consultMapper.update_consultResult(result, id);
    }


    //添加咨询类型
    @Override
    public void update_consultType(String type,Long id){
        consultMapper.update_consultType(type, id);
    }

    //查看咨询表（根据状态）
    @Override
    public List<ConsultVO> selectConsult(Long id, Integer status){
        System.out.println("mapper:"+consultMapper.selectConsult(id, status));
        return consultMapper.selectConsult(id, status);
    }
}
