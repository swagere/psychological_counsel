package com.caper.psychological_counseling.service;

import com.caper.psychological_counseling.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsultRecordService extends IService<ConsultRecord> {

    //建立咨询记录
    void build_consultRecord(ConsultRecord consultRecord);

    //用户评价咨询记录
    void update_evaluate(String evaluate,Long id);



    //添加咨询记录表状态
    void update_consultRecordStatus(Integer status,Long id);


    //修改咨询记录结论
    void update_consultRecordResult(String result,Long id);



    //查看咨询记录表（根据consultId）
    List<ConsultRecord> selectConsultRecord(Long id);

    //查看咨询记录表times（根据Id）
    Integer selectConsultRecordTimes(Long id);

    //查看咨询记录表的咨询表id（根据Id）
    Long selectConsultId(Long id);


}
